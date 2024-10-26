package com.sumanthacademy.myapplication

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval:Int = 1000,
    private var onSafeClick:(View) -> Unit
):View.OnClickListener {

    private var lastClicedTime:Long = 0

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClicedTime < defaultInterval){
            return
        }
        onSafeClick(view)
    }
}