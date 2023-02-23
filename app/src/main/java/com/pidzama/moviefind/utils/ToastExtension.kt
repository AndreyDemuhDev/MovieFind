package com.pidzama.moviefind.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(textRes:Int) {
    Toast.makeText(this,textRes, Toast.LENGTH_SHORT).show()
}