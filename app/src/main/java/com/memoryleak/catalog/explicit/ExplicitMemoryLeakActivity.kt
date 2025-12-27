package com.memoryleak.catalog.explicit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R
import com.memoryleak.catalog.explicit.staticview.StaticViewActivity
import com.memoryleak.catalog.implicit.innerclasses.InnerClassActivity

class ExplicitMemoryLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_memory_leak)
        findViewById<Button>(R.id.btn_static_view).setOnClickListener {
            startActivity(Intent(this, StaticViewActivity::class.java))
        }

    }
}