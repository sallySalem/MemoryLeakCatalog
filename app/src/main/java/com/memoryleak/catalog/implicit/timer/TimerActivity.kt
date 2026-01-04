package com.memoryleak.catalog.implicit.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R
import java.util.Timer
import java.util.TimerTask

class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            //no need to rotate the device
            override fun run() {
                runOnUiThread {
                }

                // OR
                // println(this@TimerActivity) //no need to rotate the device
            }
        }, 1000, 1000)
    }
}