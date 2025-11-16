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
import java.lang.ref.WeakReference

class HandlerActivity : AppCompatActivity() {

//    private var innerHandler: Handler = Handler(Looper.getMainLooper())
     private val innerHandler: Handler = Handler(Looper.getMainLooper()) { msg: Message ->
        println(this@HandlerActivity)
        true
    }

//    private class MyHandler(val activity: WeakReference<HandlerActivity>) :
//        Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            println(activity.get())
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        /*
         * Scheduling Runnable example for leak and fix
         */

//        innerHandler.postDelayed({
//            println(this@HandlerActivity)
//        }, 50000)

        //Fix memory leak using static inner class and use weakReference for the object used by handler
//        val activity = WeakReference(this)
//        MyHandler(activity).postDelayed({
//            println(activity.get())
//        }, 50000)


        /*
         * Scheduling Message example for leak and fix
         */
        //Scheduling Message
        val message: Message = innerHandler.obtainMessage(1)
        innerHandler.sendMessageDelayed(message, 50000)

        //Fix memory leak using static inner class and use weakReference for the object used by handler
//        val activity = WeakReference(this)
//        val myHandler = MyHandler(activity)
//        val message: Message = myHandler.obtainMessage(1)
//        myHandler.sendMessageDelayed(message, 50000)
    }

    override fun onDestroy() {
        super.onDestroy()
//        anonymousHandler.removeCallbacksAndMessages(null)
        innerHandler.removeCallbacksAndMessages(null)
    }
}