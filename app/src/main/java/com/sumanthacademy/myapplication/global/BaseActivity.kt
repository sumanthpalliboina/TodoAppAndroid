package com.sumanthacademy.myapplication.global

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sumanthacademy.myapplication.R

open class BaseActivity: AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        GlobalBusUtil.getBus().register(this)
    }

    override fun onStop() {
        GlobalBusUtil.getBus().unregister(this)
        super.onStop()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun finishAffinity() {
        super.finishAffinity()
        overridePendingTransitionExit()
    }

    @Suppress("DEPRECATION")
    private fun overridePendingTransitionEnter(){
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
    }

    @Suppress("DEPRECATION")
    private fun overridePendingTransitionExit(){
        overridePendingTransition(R.anim.slide_to_left,R.anim.slide_from_right)
    }
}