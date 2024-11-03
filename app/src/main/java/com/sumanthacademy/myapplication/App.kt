package com.sumanthacademy.myapplication

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sumanthacademy.myapplication.util.AppConstants

class App:Application() {
    companion object{
        lateinit var instance:App
        lateinit var sharedPref:SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val masterKey = MasterKey.Builder(instance)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPref = EncryptedSharedPreferences.create(
            instance,
            AppConstants.SHARED_PREFERENCE_FILENAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        registerActivityCallBacks()
    }

    fun registerActivityCallBacks(){
        registerActivityLifecycleCallbacks(object:ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                println("onCreated")
                //restrict screen shots and record
                activity.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)
            }

            override fun onActivityStarted(p0: Activity) {
                println("onStarted")
            }

            override fun onActivityResumed(p0: Activity) {
                println("onResumed")
            }

            override fun onActivityPaused(p0: Activity) {
                println("onPaused")
            }

            override fun onActivityStopped(p0: Activity) {
                println("onStopped")
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                println("onActivitySavedInstanceState")
            }

            override fun onActivityDestroyed(p0: Activity) {
                println("onDestroyed")
            }

        })
    }
}