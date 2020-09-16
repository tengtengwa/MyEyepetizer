package com.example.base.utils

import android.util.Log

/**
 * 日志的工具类
 */

//Android中避免使用enum枚举类，因为它编译出来的大小较常量大很多，而且内存占用也较大
const val VERBOSE = 0
const val DEBUG = 1
const val WARNING = 2
const val ERROR = 3


private val level = if (GlobalUtil.isApkInDebug()) VERBOSE else ERROR

fun logV(TAG: String, message: String) {
    if (level <= VERBOSE) {
        Log.v(TAG, message)
    }
}

fun logD(TAG: String, message: String) {
    if (level <= DEBUG) {
        Log.d(TAG, message)
    }
}

fun logW(TAG: String, message: String) {
    if (level <= WARNING) {
        Log.w(TAG, message)
    }
}

fun logE(TAG: String, message: String) {
    if (level <= ERROR) {
        Log.e(TAG, message)
    }
}