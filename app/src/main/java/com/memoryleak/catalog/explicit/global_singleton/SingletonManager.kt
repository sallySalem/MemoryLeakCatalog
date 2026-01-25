package com.memoryleak.catalog.explicit.global_singleton

import android.content.Context

class SingletonManager private constructor(context: Context) {

    private var localContext: Context? = context

    companion object {
        @Volatile
        private var instance: SingletonManager? = null

        fun getInstance(context: Context): SingletonManager {

            return instance ?: synchronized(this) {
                instance ?: SingletonManager(context).also { instance = it }
            }
        }
    }
}