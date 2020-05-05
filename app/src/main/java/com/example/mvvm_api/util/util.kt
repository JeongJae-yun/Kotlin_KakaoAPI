package com.example.mvvm_api.util

import android.util.Range

class util() {
    fun changeDateTime(datetime : String) : String {
        val a = datetime
        val b = IntRange(0,9)
        return a.slice(b)
    }

    fun changeTime(time : Int) : String{
        val min = time / 60
        val sec = time % 60
        return "$min : $sec"
    }

}
