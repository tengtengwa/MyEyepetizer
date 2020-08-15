package com.example.base.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * 一些全局工具函数
 */


/**
 * 懒人打印日志函数
 */
fun logD(TAG: String, message: String) = Log.d(TAG, message)


/**
 * 批量注册监听器的方法
 *
 * @param views 所有需要设置监听器的view对象
 * @param block 每个view的onClick方法中要执行的代码段，应该由一个when语句根据不同的view执行不同的操作
 */
fun setOnClickListener(vararg views: View?, block: View.() -> Unit) {
    //返回的是一个OnClickListener接口的实例，在onClick方法中调用block函数
    val listener = View.OnClickListener { it.block() }
    //遍历views，给它们设置监听器
    views.forEach { it?.setOnClickListener(listener) }
}

/**
 * toast的懒人方法，默认时间为short
 */
fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, this, duration).show()
