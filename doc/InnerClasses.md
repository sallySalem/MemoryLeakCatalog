## Inner Classes

Is a non-static nested classes have all the features as a regular class plus they have full access  to the outer class.

Inner classes provide a way to encapsulate functionality within a single class, improving code organization and reducing complexity. 

They can also be used to implement callbacks, listeners, and other event-driven functionality.

### Memory leak
The inner class will cause memory leak because it has an implicit reference to its outer class 
and the outer class instance holds a reference to the inner class instance, 
then the inner class instance cannot be garbage collected even if it's no longer needed.

| Inner classs | Normal nested class | 
| --- | --- |
| ![Screenshot 2023-02-01 at 7 35 23 PM](https://user-images.githubusercontent.com/2602891/216132199-11dd0d20-17d6-4f5a-916f-e89104e6e887.png) |![Screenshot 2023-02-01 at 7 34 09 PM](https://user-images.githubusercontent.com/2602891/216131872-9948ae33-7a71-45cd-9cf8-1a2fdc5a1504.png) | 


By default the nested classes in kotlin is static.
Tools > kotlin > Show kotlin byte code > Decompile

| Inner classs byte code | Normal nested class byte code| 
| --- | --- |
|![Screenshot 2023-02-01 at 7 47 19 PM](https://user-images.githubusercontent.com/2602891/216134958-5789b673-f991-43bd-95ca-910863e1a032.png) |![Screenshot 2023-02-01 at 7 44 43 PM](https://user-images.githubusercontent.com/2602891/216134313-c747f709-dde8-4802-bcef-cb138de7c47b.png) |

### Inner Class memory leak Example

### Example

- Create new activity and add inner class in it.
```Kotlin
    inner class InnerClass {
      fun printMessage() {
        println("Hello")
      }
    }
```
- Create static reference to inner class to create reference out of activity.
```Kotlin
    companion object {
        private lateinit var innerClass: InnerClass
    }
```
- Run the app and rotate the activity.
* Then check memory leak from profiler

| Old Android studio                                                                                                                                                                                                                                         | New Android studio                                                                                                                           | 
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| <img width="334" alt="Screenshot 2023-02-01 at 8 03 07 PM" src="https://user-images.githubusercontent.com/2602891/216139722-d2060e5b-d066-408a-ae66-5de58793bf0d.png">   <br/>  Run “Force garbage collection” then select “Capture heap dump” and start record |![leak_in_AS.png](../media/leak_in_AS.png)|
* The heap dump output
![inner_class_example_1_heapdump.png](../media/inner_class_example_1_heapdump.png)
##### To fix this Memory leak you need to use static inner class by delete `inner` keyword
```Kotlin
    class InnerClass {
    }
```