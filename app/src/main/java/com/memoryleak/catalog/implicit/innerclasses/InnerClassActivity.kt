package com.memoryleak.catalog.implicit.innerclasses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R

class InnerClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inner_class)

        innerClass =  InnerClass()
        innerClass.printMessage()

    }

    class InnerClass {
        fun printMessage() {
            println("Hello")
        }
    }

    companion object {
        private lateinit var innerClass: InnerClass
    }
}

/*
* Memory leak in inner class
* Example #1
*
*
 class InnerClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inner_class)

        innerClass =  InnerClass()
        innerClass.printMessage()

    }

    inner class InnerClass {
        fun printMessage() {
            println("Hello")
        }
    }

    companion object {
        private lateinit var innerClass: InnerClass
    }
}
 */