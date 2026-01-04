package com.memoryleak.catalog.implicit.timertask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R
import java.util.Timer
import java.util.TimerTask

class TimerTaskActivity : AppCompatActivity() {
    private lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)


        timer = Timer()
        timer.schedule(object : TimerTask() {
            // Even an empty implementation can cause a leak.
            // The reference exists whether it's used or not.
            override fun run() {
                runOnUiThread {
                }
            }
        }, 1000, 1000)
    }
}