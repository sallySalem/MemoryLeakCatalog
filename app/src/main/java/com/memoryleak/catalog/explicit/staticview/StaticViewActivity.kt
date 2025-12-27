package com.memoryleak.catalog.explicit.staticview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R
import java.lang.ref.WeakReference
import java.util.Timer
import java.util.TimerTask

class StaticViewActivity : AppCompatActivity() {

    //Example 1: Static reference to a View
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        staticTextView = TextView(this)
//        staticTextView.text = " Static Text view...."
//        setContentView(staticTextView)
//    }
//
//    companion object {
//        lateinit var staticTextView: TextView
//    }


    //Example 2: Static reference to a View
    // Using TimerTask with WeakReference to Activity.
    // But the textView is not a weak reference, so it cause memory leak.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_view)

        val textView = findViewById<TextView>(R.id.txt_example)
        val weakActivity = WeakReference(this)
        val timer = Timer()

        // The long-running TimerTask holds a strong reference to the TextView,
        // which in turn holds a reference to the Activity, causing a leak.
        // Without rotate the screen just passing the textView directly cause memory leak.
        timer.schedule(MyTimerTask(weakActivity, textView), 1000, 1000)
    }

    class MyTimerTask(private val activityReference: WeakReference<StaticViewActivity>, private val textView: TextView) :
        TimerTask() {
        override fun run() {
            val activity: StaticViewActivity? = activityReference.get()
            if (activity != null) {
                activity.runOnUiThread {
                    textView.text = "Updated text .................."
                }
            } else {
                cancel()
            }
        }
    }
}