package com.example.tweetsy.utils

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext


object AppUtils {
    fun showToast(context: Context, message: String) {
        context?.let {
            Toast.makeText(it, message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}