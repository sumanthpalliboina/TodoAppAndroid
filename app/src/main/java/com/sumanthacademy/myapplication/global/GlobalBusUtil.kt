package com.sumanthacademy.myapplication.global

import org.greenrobot.eventbus.EventBus

class GlobalBusUtil {
    companion object {
        var bus:EventBus? = null

        fun getEventBus(): EventBus {
            if (bus == null) {
                bus = EventBus.getDefault()
            }

            return bus!!    //!!-non null assertion if null throws null pointer exception. if u don't want throws nullpointerexception, use ? safety null
        }
    }
}