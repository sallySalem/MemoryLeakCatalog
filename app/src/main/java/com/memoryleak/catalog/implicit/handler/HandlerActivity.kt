package com.memoryleak.catalog.implicit.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class HandlerActivity : AppCompatActivity() {

    private var innerHandler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        //Scheduling Runnable
        innerHandler.postDelayed({
            println(this@HandlerActivity)
        }, 50000)
    }
}