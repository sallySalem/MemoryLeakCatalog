package com.memoryleak.catalog.implicit.thread

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class RunnableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runnable)
//        val runnable = MyRunnable() // Inner class holds a reference.
//        Thread(runnable).start()

        // Bad: Anonymous Runnable holds an implicit reference.
        val runnable = object : Runnable {
            override fun run() {
                try {
                    Thread.sleep(20000)
                    this@RunnableActivity //Cause ML
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        Thread(runnable).start()
    }

//    // Bad: Inner class holds an implicit reference to ThreadActivity.
//    private inner class MyRunnable : Runnable {
//        override fun run() {
//            try {
//                Thread.sleep(20000)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//        }
//    }
}