package com.example.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 主页面Fragment间进行通信的“工具ViewModel”
 */
class MainViewModel : ViewModel() {

    var isRefreshHomePage = MutableLiveData<Boolean>(false)

    var isRefreshCommunityPage = MutableLiveData<Boolean>(false)

    var isRefreshNotificationPage = MutableLiveData<Boolean>(false)

    var isRefreshProfilePage = MutableLiveData<Boolean>(false)

    val homePageRefresh: MutableLiveData<Int> = MutableLiveData(-1)

    val communityPageRefresh: MutableLiveData<Int> = MutableLiveData(-1)

    val notificationPageRefresh: MutableLiveData<Int> = MutableLiveData(-1)

    companion object {
        /**
         * 将上面三个livedata的值更新为下面的值之一以通知相应的Fragment刷新数据
         */
        const val REFRESH_HOME_DAILY = 1
        const val REFRESH_HOME_DISCOVERY = 2
        const val REFRESH_HOME_RECOMMEND = 3

        const val REFRESH_COMMUNITY_RECOMMEND = 4
        const val REFRESH_COMMUNITY_FOLLOW = 5

        const val REFRESH_NOTIFICATION_PUSH = 6
    }
}