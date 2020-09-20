package com.example.base.utils

import com.example.base.MyApplication

object DensityUtil {

    fun getScreenPx(): String {
        MyApplication.context.resources.displayMetrics.apply {
            return "${widthPixels}X${heightPixels}"
        }
    }

    fun dp2px(dpValue: Float): Int {
        val scale = MyApplication.context.resources.displayMetrics.scaledDensity
        return (scale * dpValue + 0.5f).toInt()
    }

    fun px2dp(pxValue : Float): Int {
        val scale = MyApplication.context.resources.displayMetrics.scaledDensity
        return (pxValue / scale - 0.5f).toInt()
    }
}