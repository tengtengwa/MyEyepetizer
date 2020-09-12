package com.example.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.main.utils.Event

/**
 * 主页面Activity和Fragment、Fragment间进行通信的“工具ViewModel”
 */
class MainViewModel : ViewModel() {

    private val _refreshPageEvent = MutableLiveData<Event<Class<*>?>>()

    private val _switchPageEvent = MutableLiveData<Event<Class<*>?>>()

    //下面这些LiveData供外部观察，上面这些仅供公共刷新方法内部更改
    val refreshPageEvent: MutableLiveData<Event<Class<*>?>>
        get() = _refreshPageEvent

    val switchPagerEvent: MutableLiveData<Event<Class<*>?>>
        get() = _switchPageEvent

    fun refreshPage(clazz: Class<*>? = null) {
        _refreshPageEvent.value = Event(clazz)
    }

    /**
     * 此方法用于通知MainActivity切换页面
     * @param clazz 需要切换到的Fragment的java类
     */
    fun switchMainPage(clazz: Class<*>? = null) {
        _switchPageEvent.value = Event(clazz)
    }
}