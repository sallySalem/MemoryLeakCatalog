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