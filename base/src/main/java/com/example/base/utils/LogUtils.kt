package com.example.base.utils

import android.util.Log

/**
 * 日志的工具类
 */

private enum class LogLevel {
    VERBOSE, DEBUG, WARNING, ERROR
}

private val level = if (GlobalUtil.isApkInDebug()) LogLevel.VERBOSE else LogLevel.ERROR

fun logV(TAG: String, message: String) {
    if (level <= LogLevel.VERBOSE) {
        Log.v(TAG, message)
    }
}

fun logD(TAG: String, message: String) {
    if (level <= LogLevel.DEBUG) {
        Log.d(TAG, message)
    }
}

fun logW(TAG: String, message: String) {
    if (level <= LogLevel.WARNING) {
        Log.w(TAG, message)
    }
}

fun logE(TAG: String, message: String) {
    if (level <= LogLevel.ERROR) {
        Log.e(TAG, message)
    }
}