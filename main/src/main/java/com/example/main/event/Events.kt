package com.example.main.event

/**
 * 所有事件的基类
 */
open class MessageEvent

/**
 * 刷新事件，用于通知相应的Fragment进行刷新页面的操作
 */
class RefreshEvent(var clazz: Class<*>? = null) : MessageEvent()

/**
 * 切换页面事件，用于通知MainActivity切换页面和底部按钮选中样式
 */
class SwitchPageEvent(var clazz: Class<*>? = null) : MessageEvent()