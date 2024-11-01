package com.sumanthacademy.myapplication.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.app.JobIntentService

@Suppress("DEPRECATION")
class PositiveBtnNotificationService: JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        println("Debugm->service handle work called")
        Handler(Looper.getMainLooper()).post{  //post toast to main thread from background
            //it is displaying when application load
            Toast.makeText(applicationContext,"positive btn is clicked and listened using service",Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val JOB_ID = 1000

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context,PositiveBtnNotificationService::class.java, JOB_ID,intent)
        }
    }
}