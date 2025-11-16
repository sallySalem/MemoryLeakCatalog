package com.memoryleak.catalog.implicit.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class HandlerActivity : AppCompatActivity() {

//    private var innerHandler: Handler = Handler(Looper.getMainLooper())
     private val innerHandler: Handler = Handler(Looper.getMainLooper()) { msg: Message ->
        println(this@HandlerActivity)
        true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        //Scheduling Runnable
//        innerHandler.postDelayed({
//            println(this@HandlerActivity)
//        }, 50000)


        //Scheduling Message
        val message: Message = innerHandler.obtainMessage(1)
        innerHandler.sendMessageDelayed(message, 50000)
    }

    override fun onDestroy() {
        super.onDestroy()
        innerHandler.removeCallbacksAndMessages(null)
    }
}