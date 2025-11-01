package com.memoryleak.catalog.implicit.anonymous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R
import java.lang.ref.WeakReference

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

open class MyAnonymousClass {
    open fun doSomething() {
    }
}


/*
* Memory leak in anonymous class
*
*
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
 */