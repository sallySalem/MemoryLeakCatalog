package com.memoryleak.catalog.explicit.global_singleton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.memoryleak.catalog.R

class GlobalSingletonActivity : AppCompatActivity() {

    private lateinit var myManager: SingletonManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_singleton)

        myManager = SingletonManager.getInstance(this)
    }
}