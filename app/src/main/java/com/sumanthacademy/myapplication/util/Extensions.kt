package com.sumanthacademy.myapplication.util

import android.view.View
import com.sumanthacademy.myapplication.SafeClickListener
import java.util.Calendar

fun View.setSafeOnClickListener(onSafeClick:(View) -> Unit){
    val safeClickListener = SafeClickListener{
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Calendar.setExactHrAndMinute(hr:Int,min:Int): Calendar{
    this.set(Calendar.HOUR_OF_DAY,hr)
    this.set(Calendar.MINUTE,min)
    this.set(Calendar.SECOND,0)
    this.set(Calendar.MILLISECOND,0)

    return this
}