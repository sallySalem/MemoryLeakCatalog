package com.memoryleak.catalog.implicit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R
import com.memoryleak.catalog.implicit.innerclasses.InnerClassActivity

class ImplicitMemoryLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_memory_leak)

        findViewById<Button>(R.id.btn_inner_class).setOnClickListener {
            startActivity(Intent(this, InnerClassActivity::class.java))
        }
    }
}