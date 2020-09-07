package com.example.base.event

/**
 * 切换页面事件，用于通知MainActivity切换页面和底部按钮选中样式
 */
class SwitchPageEvent(var clazz: Class<*>? = null) : MessageEvent()