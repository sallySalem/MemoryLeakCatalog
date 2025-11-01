## Anonymous Class
An unnamed class defined within a method. The syntax of an anonymous class expression is like the invocation of a constructor, except that there is a class definition contained in a block of code. 

Anonymous classes help make code more concise and reduce boilerplate, since you can declare and create the class at the same time.

They are commonly used to quickly implement interfaces, override methods, or define short-lived event handlers without creating a separate named class.

**Interface**
```kotlin
    val anonymousInterface = object : Runnable {
        override fun run() {
    
            }
    }
    
    // or lambda
    val anonymousInterface = Runnable { }
```

**Class**

```kotlin
    val mObject = object : MyAnonymousClass() { 
        override fun doSomething() {
        
        } 
    }
```

### Memory leak
Occur when an anonymous class holds a reference to an outer class or activity, preventing it from being garbage collected even when it's no longer needed.

**Example**

```kotlin
    class AnonymousActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_anonymous)
    
            anonymousObject = object : MyAnonymousClass() {
                override fun doSomething() {
                    this@AnonymousActivity
                }
            }
        }
    
        companion object {
            private lateinit var anonymousObject: MyAnonymousClass
        }
    }
    
    open class MyAnonymousClass {
        open fun doSomething() {
        }
    }
```

- Run the app and rotate the activity.
- Then check memory leak from profiler
  <img src="../media/anonymous_example.png"  style="border: 1px solid #000;">



##### To fix this Memory leak you need to use WeakReference
Wrap `MyAnonymousClass` with WeakReference, as anonymous classes don't have explicit names or variable references.

```kotlin
class AnonymousActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous)

        anonymousObject = WeakReference(object : MyAnonymousClass() {
            override fun doSomething() {
                this@AnonymousActivity
            }
        })
    }

    companion object {
        private lateinit var anonymousObject: WeakReference<MyAnonymousClass>
    }
}
```