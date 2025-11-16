# Handler
There are two main uses for a Handler:
1. Schedule messages and runnables to be executed at some point in the future.
2. Enqueue an action to be performed on a different thread than your own.

The main difference lies in the timing and order  of execution:
- scheduling tasks involves specifying when a task should run
- enqueuing tasks involves maintaining the order of execution.

|          | Scheduling                                                                     | Enqueuing                   |
|----------|--------------------------------------------------------------------------------|-----------------------------|
| Runnable | postAtTime(Runnable, long) <br/> postDelayed(Runnable, Object, long) | post(Runnable)              |
| Message  |                    sendMessageAtTime(Message, long)<br/> sendMessageDelayed(Message, long)                                                             |     sendEmptyMessage(int)<br/> sendMessage(Message)                        |


Handler usually execute on the main/UI thread.

Handler creation can be inner class or anonymous class the main difference is the inner class will have a callback

### Handler as inner class
```kotlin
private var innerHandler: Handler = Handler(Looper.getMainLooper())
```
Or
```kotlin
  private val innerHandler: Handler = Handler(Looper.getMainLooper()) { msg: Message ->
        true
    }
```
_**Bytecode**_
```java
 private final Handler innerHandler = new Handler(Looper.getMainLooper(), (Handler.Callback)(new Handler.Callback() {
      public final boolean handleMessage(@NotNull Message msg) {
         Intrinsics.checkNotNullParameter(msg, "msg");
         return true;
      }
   }));
```

### Handler as anonymous class
```kotlin
 private val anonymousHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
        }
    }
```
_**Bytecode**_
```java
 private final Handler anonymousHandler = (Handler)(new Handler(Looper.getMainLooper()) {
      public void handleMessage(@NotNull Message msg) {
         Intrinsics.checkNotNullParameter(msg, "msg");
      }
   });
```


## Memory leak
### Inner Handler
###### Scheduling Runnable
```kotlin
    private var innerHandler: Handler = Handler(Looper.getMainLooper())   
    
    //Scheduling Runnable
    innerHandler.postDelayed({
        println(this@HandlerActivity)
    }, 50000)
```
###### Scheduling Message
```kotlin
     private val innerHandler: Handler = Handler(Looper.getMainLooper()) { msg: Message ->
        println(this@HandlerActivity)
        true
    }

    //Scheduling Message
    val message: Message = innerHandler.obtainMessage(1)
    innerHandler.sendMessageDelayed(message, 50000)
```
**The profile output:**
![handler_schedule_ml.png](../media/handler_schedule_ml.png)

### Anonymous Handler
```kotlin
    private val anonymousHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            println(this@HandlerActivity)
        }
    }
```


###### Scheduling Runnable
```kotlin
    anonymousHandler.postDelayed({ 
        println(this@HandlerActivity) 
    }, 50000)
```
###### Scheduling Message
```kotlin
    val message: Message = anonymousHandler.obtainMessage(1)
    anonymousHandler.sendMessageDelayed(message, 50000)
```

The Enqueuing handler also could cause a memory leak

`post(Runnable)`->  If the Runnable has too long task.

`sendEmptyMessage(int)` or `sendMessage(Message)` -> If the queue has too much message and close the activity before complete it.

## Fix Memory leak
### Remove the Callbacks
To removes any pending messages and callbacks that are associated with the handler.
`removeCallbacksAndMessages` doesn't affect the messages that are currently being processed.
```kotlin
    override fun onDestroy() {
        super.onDestroy()
        innerHandler.removeCallbacksAndMessages(null)
    }
```
### Use static inner class and use WeakReference for the object used by handler
```kotlin
 private class MyHandler(val activity: WeakReference<HandlerActivity>): Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            println(activity.get())
        }
    }
```
_Runnable_
```kotlin
    val activity = WeakReference(this)
    MyHandler(activity).postDelayed({
        println(activity.get())
    }, 50000)

```
_Message_
```kotlin
    val activity = WeakReference(this)
    val myHandler = MyHandler(activity)
    val message: Message = myHandler.obtainMessage(1)
    myHandler.sendMessageDelayed(message, 50000)
```


