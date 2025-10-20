# Memory leak catalog

Memory leak occurs when an application keeps holding on to memory that is no longer needed
, causing the device run out of memory.
The heap is a region of memory in which objects are allocated during the runtime of an Android application.
and out of memory happens when the there is no much heap space available on the device.

Directly or indirectly referencing objects can cause memory leaks.



## Implicit Reference (Indirect)
That occur when an object holds a reference to another object that indirectly causes a memory leak.
This one is hard to find and fix as you don't have direct reference to release.

### Nested Class
The nested class is a class that is defined inside another class. In Android, nested classes are commonly used to define a custom view or adapter, or to implement a listener interface for a UI component.

Nested Class cause memory leak if they hold a reference to their parent class. So the parent class is not properly released when no longer needed.

There are four types of nested classes: Static nested classes, non-static nested classes (inner classes), local classes, and anonymous classes.
In android the most use are Inner class and Anonymous class.


* [Inner Classes](/doc/InnerClasses.md)
* [Anonymous classes](/doc/AnonymousClass.md)

Under nested classes (Inner or Anonymous) there are multiple cases
* ....