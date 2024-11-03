package com.sumanthacademy.myapplication.global

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sumanthacademy.myapplication.R
import com.sumanthacademy.myapplication.model.Todo
import org.greenrobot.eventbus.Subscribe

open class BaseActivity: AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        GlobalBusUtil.getEventBus().register(this)
    }

    override fun onStop() {
        GlobalBusUtil.getEventBus().unregister(this)
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

    /***
     * this function is mandatory for avoid event bus package issue of finding at-least one subscribe method in super class for all classes
     * keep it open this method to overide this super class method in sub classes. Otherwise, error will comes
      */
    @Subscribe
    open fun onResponse(todo: Todo){

    }
}