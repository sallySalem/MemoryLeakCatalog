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

        // The thread is created as an inner class and started.
        // It now has an implicit reference to ThreadActivity.
        BackgroundTask().start()

    }

    // Bad: Inner class holds an implicit reference to ThreadActivity
    private inner class BackgroundTask : Thread() {
        override fun run() {
            try {
                sleep(20000) // Simulate a long-running task
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}