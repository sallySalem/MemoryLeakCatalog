package com.memoryleak.catalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.explicit.ExplicitMemoryLeakActivity
import com.memoryleak.catalog.implicit.ImplicitMemoryLeakActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_Implicit_Reference).setOnClickListener {
            startActivity(Intent(this, ImplicitMemoryLeakActivity::class.java))
        }

        findViewById<Button>(R.id.but_explicit_reference).setOnClickListener {
            startActivity(Intent(this, ExplicitMemoryLeakActivity::class.java))
        }
    }
}