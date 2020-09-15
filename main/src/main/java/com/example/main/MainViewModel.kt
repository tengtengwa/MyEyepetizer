package com.example.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.main.utils.Event

/**
 * 主页面Activity和Fragment、Fragment间进行通信的“工具ViewModel”
 */
class MainViewModel : ViewModel() {

    //下面这些LiveData供外部观察，上面这些仅供公共刷新方法内部更改
    val refreshPageEvent = MutableLiveData<Event<Class<*>?>>()

    val switchPageEvent = MutableLiveData<Event<Class<*>?>>()

    fun refreshPage(clazz: Class<*>? = null) {
        refreshPageEvent.value = Event(clazz)
    }

    /**
     * 此方法用于通知MainActivity切换页面
     * @param clazz 需要切换到的Fragment的java类
     */
    fun switchPage(clazz: Class<*>? = null) {
        switchPageEvent.value = Event(clazz)
    }
}