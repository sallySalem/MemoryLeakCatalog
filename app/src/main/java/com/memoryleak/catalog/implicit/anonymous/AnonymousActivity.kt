package com.memoryleak.catalog.implicit.anonymous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R

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