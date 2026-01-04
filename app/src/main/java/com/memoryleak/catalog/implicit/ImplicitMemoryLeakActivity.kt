package com.memoryleak.catalog.implicit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R
import com.memoryleak.catalog.implicit.anonymous.AnonymousActivity
import com.memoryleak.catalog.implicit.asynctask.AsyncTaskActivity
import com.memoryleak.catalog.implicit.handler.HandlerActivity
import com.memoryleak.catalog.implicit.innerclasses.InnerClassActivity
import com.memoryleak.catalog.implicit.thread.RunnableActivity
import com.memoryleak.catalog.implicit.thread.ThreadActivity
import com.memoryleak.catalog.implicit.timertask.TimerTaskActivity

class ImplicitMemoryLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_memory_leak)

        findViewById<Button>(R.id.btn_inner_class).setOnClickListener {
            startActivity(Intent(this, InnerClassActivity::class.java))
        }

        findViewById<Button>(R.id.btn_anonymous_class).setOnClickListener {
            startActivity(Intent(this, AnonymousActivity::class.java))
        }

        findViewById<Button>(R.id.btn_handler).setOnClickListener {
            startActivity(Intent(this, HandlerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_thread).setOnClickListener {
            startActivity(Intent(this, ThreadActivity::class.java))
        }

        findViewById<Button>(R.id.btn_runnable).setOnClickListener {
            startActivity(Intent(this, RunnableActivity::class.java))
        }

        findViewById<Button>(R.id.btn_async_task).setOnClickListener {
            startActivity(Intent(this, AsyncTaskActivity::class.java))
        }

        findViewById<Button>(R.id.btn_timer).setOnClickListener {
            startActivity(Intent(this, TimerTaskActivity::class.java))
        }
    }
}