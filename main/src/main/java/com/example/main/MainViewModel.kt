package com.example.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.main.utils.Event

/**
 * 主页面Activity和Fragment、Fragment间进行通信的“工具ViewModel”
 */
class MainViewModel : ViewModel() {

    /**
     * MainActivity -> 主页面中四个Fragment
     */
    private val _isRefreshHomePage = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshCommunityPage = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshNotificationPage = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshProfilePage = MutableLiveData<Event<Boolean>>(Event(false))

    /**
     * HomeFragment -> ViewPager中三个Fragment
     */
    private val _isRefreshHomeDiscovery = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshHomeRecommend = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshHomeDaily = MutableLiveData<Event<Boolean>>(Event(false))

    /**
     * CommunityFragment -> ViewPager中两个Fragment
     */
    private val _isRefreshCommunityRecommend = MutableLiveData<Event<Boolean>>(Event(false))

    private val _isRefreshCommunityFollow = MutableLiveData<Event<Boolean>>(Event(false))

    /**
     * NotificationFragment -> ViewPager中一个Fragment
     */
    private val _isRefreshNotificationPush = MutableLiveData<Event<Boolean>>(Event(false))

    //下面这些LiveData供外部观察，上面这些仅供公共刷新方法内部更改
    val isRefreshHomePage: LiveData<Event<Boolean>>
        get() = _isRefreshHomePage

    val isRefreshCommunityPage: MutableLiveData<Event<Boolean>>
        get() = _isRefreshCommunityPage

    val isRefreshNotificationPage: MutableLiveData<Event<Boolean>>
        get() = _isRefreshNotificationPage

    val isRefreshProfilePage: MutableLiveData<Event<Boolean>>
        get() = _isRefreshProfilePage

    val isRefreshHomeDiscovery: MutableLiveData<Event<Boolean>>
        get() = _isRefreshHomeDiscovery

    val isRefreshHomeRecommend: MutableLiveData<Event<Boolean>>
        get() = _isRefreshHomeRecommend

    val isRefreshHomeDaily: MutableLiveData<Event<Boolean>>
        get() = _isRefreshHomeDaily

    val isRefreshCommunityRecommend: MutableLiveData<Event<Boolean>>
        get() = _isRefreshCommunityRecommend

    val isRefreshCommunityFollow: MutableLiveData<Event<Boolean>>
        get() = _isRefreshCommunityFollow

    val isRefreshNotificationPush: MutableLiveData<Event<Boolean>>
        get() = _isRefreshNotificationPush

    fun refreshHomePage() {
        _isRefreshHomePage.value = Event(true)
    }

    fun refreshHomeDaily() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refreshHomeDiscovery() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refreshHomeRecommend() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refreshCommunityPage() {
        _isRefreshCommunityPage.value = Event(true)
    }

    fun refreshCommunityRecommend() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refresCommunityFollow() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refreshNotificationPage() {
        _isRefreshNotificationPage.value = Event(true)
    }

    fun refreshNotificationPush() {
        _isRefreshHomeDiscovery.value = Event(true)
    }

    fun refreshProfilePage() {
        _isRefreshProfilePage.value = Event(true)
    }


/*    companion object {
        //将上面三个livedata的值更新为下面的值之一以通知相应的Fragment刷新数据
        //home页面
        const val REFRESH_HOME_DAILY = 1
        const val REFRESH_HOME_DISCOVERY = 2
        const val REFRESH_HOME_RECOMMEND = 3

        //社区页面
        const val REFRESH_COMMUNITY_RECOMMEND = 4
        const val REFRESH_COMMUNITY_FOLLOW = 5

        //通知页面
        const val REFRESH_NOTIFICATION_PUSH = 6
    }*/
}