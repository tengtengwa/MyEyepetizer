package com.example.base.event

/**
 * 刷新事件，用于通知相应的Fragment进行刷新页面的操作
 */
class RefreshEvent(var clazz: Class<*>? = null) : MessageEvent()