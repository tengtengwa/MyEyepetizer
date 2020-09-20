package com.example.main.utils

import androidx.lifecycle.Observer
import com.example.base.utils.logD

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

    //获取事件的参数，即使它已经被处理，
    fun peekCount(): T? = content
}

/**
 * 这个自定义Observer用于简化观察LiveData时Observer中的样板代码
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        /**
         * 这里需要通知所有的事件接受者，所以调用peekCount函数
         */
        event?.peekCount()?.let { value ->
            logD("refreshPageEvent", "class:${value}")
            onEventUnhandledContent(value)
        }
    }
}