package com.example.main.utils

/**
 * 数据的包装类，通过代表一个事件的LiveData暴露给外部
 */
open class Event<out T>(private val content: T) {   //使用了泛型的协变，只允许get，不允许set

    var handled = false
        private set

    fun getEventIfNotHandled(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }

    fun peekCount(): T? = content
}