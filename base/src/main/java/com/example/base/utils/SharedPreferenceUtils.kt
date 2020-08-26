package com.example.base.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.base.MyApplication

/**
 * SP的工具类，简化使用，下面get函数还有点问题
 */
object SharedPreferenceUtils {

    private val sp =
        MyApplication.context.getSharedPreferences("Global_SharedPreference", Context.MODE_PRIVATE)

    fun writeSP(pair: Pair<String, *>) {
        sp.edit().put(pair).apply()
    }

    fun <T> readSP(key: String, defValue: T) = get(key, defValue)

    private fun SharedPreferences.Editor.put(pair: Pair<String, *>) : SharedPreferences.Editor {
        when (pair.second) {
            is Boolean -> this.putBoolean(pair.first, pair.second as Boolean)
            is Int -> this.putInt(pair.first, pair.second as Int)
            is Long -> this.putLong(pair.first, pair.second as Long)
            is Float -> this.putFloat(pair.first, pair.second as Float)
            is String -> this.putString(pair.first, pair.second as String)
        }
        return this
    }

    private fun <T> get(key: String, defValue: T): T {
        return when (defValue) {
            is Boolean -> sp.getBoolean(key, defValue)
            is Int -> sp.getInt(key, defValue)
            is Long -> sp.getLong(key, defValue)
            is Float -> sp.getFloat(key, defValue)
            is String -> sp.getString(key, defValue)
            else -> throw RuntimeException("暂不支持此类数据")
        } as T
    }
}