package com.example.akstask.view.util

import android.app.Activity
import android.util.DisplayMetrics
import com.example.akstask.R


object Utils {

    fun numberOfColumns(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        var widthDivider = 400
        if (width < 1079 && width > 1000) {
            widthDivider =450
        }
        val nColumns = width / widthDivider
        return if (nColumns < 2) 1 else nColumns
    }

    fun getRandomPic(): Int {
        val rI = (1..5).shuffled().first()
        return when(rI) {
            1 -> R.mipmap.app1
            2 -> R.mipmap.app2
            3 -> R.mipmap.app3
            4 -> R.mipmap.app4
            5 -> R.mipmap.app5
            else -> R.mipmap.app1
        }
    }

}