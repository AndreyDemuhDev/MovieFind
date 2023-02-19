package com.pidzama.moviefind.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AirplaneReceiverMode : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneMode = intent?.getBooleanExtra("state", false) ?: false
        if (isAirplaneMode) {
            Log.d("AAA", "Mode On")
        } else {
            Log.d("AAA", "Mode Off")
        }
    }
}