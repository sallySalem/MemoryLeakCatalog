package com.memoryleak.catalog.implicit.asynctask

import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.memoryleak.catalog.R

class AsyncTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        MyAsyncTask().execute()
    }

    private inner class MyAsyncTask: AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            Thread.sleep(5000)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            this@AsyncTaskActivity
        }
    }
}