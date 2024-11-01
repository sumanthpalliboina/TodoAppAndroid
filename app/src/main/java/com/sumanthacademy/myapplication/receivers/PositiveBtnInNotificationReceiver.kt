package com.sumanthacademy.myapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PositiveBtnInNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            Toast.makeText(context,"Open App and ${intent?.getStringExtra("positiveBtnClickStatus")}",Toast.LENGTH_LONG).show()
        }
    }
}