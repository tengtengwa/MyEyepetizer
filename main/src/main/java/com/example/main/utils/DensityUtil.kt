package com.example.main.utils

import com.example.base.MyApplication

object DensityUtil {

    fun getScreenPx(): String {
        MyApplication.context.resources.displayMetrics.apply {
            return "${widthPixels}X${heightPixels}"
        }
    }
}