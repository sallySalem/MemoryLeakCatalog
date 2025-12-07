package com.memoryleak.catalog.implicit.thread

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thread)

        BackgroundTask().start()

    }

    //Memory leak for inner class
    // Fix by remove inner word
    private inner class BackgroundTask : Thread() {
        override fun run() {
            sleep(20000)
        }
    }
}