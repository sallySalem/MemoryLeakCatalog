package com.memoryleak.catalog.explicit.staticview

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class StaticViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        staticTextView = TextView(this)
        staticTextView.text = " Static Text view...."
        setContentView(staticTextView)
    }

    companion object {
        lateinit var staticTextView: TextView
    }
}