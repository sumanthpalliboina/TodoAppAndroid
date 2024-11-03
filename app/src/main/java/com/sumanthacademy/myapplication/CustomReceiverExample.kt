package com.sumanthacademy.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.sumanthacademy.myapplication.util.SPUtil

class CustomReceiverExample : BroadcastReceiver() {

    var sharedPeferences:SharedPreferences? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.sumanthacademy.myapplication.SEND_EVENT"){
            var input:String? = intent.getStringExtra("data")
            Log.i("message","on receive ${input}")
            Toast.makeText(context,input.toString(),Toast.LENGTH_LONG).show()

            /*sharedPeferences = context?.getSharedPreferences("saveInput",Context.MODE_PRIVATE)
            var editor = sharedPeferences?.edit()
            editor?.putString("input",input)
            editor?.apply()*/

            SPUtil.setString("input",input.toString())

        }
    }
}