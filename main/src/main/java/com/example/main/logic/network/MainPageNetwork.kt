package com.example.main.logic.network

import com.example.main.logic.network.service.MainPageService
import com.example.main.logic.network.service.ServiceCreator

/**
 * 主页面网络请求
 */
class MainPageNetwork private constructor() {

    private val mainPageService = ServiceCreator.create<MainPageService>()

    suspend fun fetchHomeDiscovery(url: String) = mainPageService.getHomeDiscovery(url)

    suspend fun fetchHomeRecommend(url: String) = mainPageService.getHomePageRecommend(url)

    suspend fun fetchHomeDaily(url: String) = mainPageService.getHomeDaily(url)

    suspend fun fetchCommunityRecommend(url: String) = mainPageService.getCommunityRecommend(url)

    suspend fun fetchCommunityFollow(url: String) = mainPageService.getCommunityFollow(url)

    suspend fun fetchNotificationPush(url: String) = mainPageService.getNotificationPush(url)

    suspend fun fetchHotSearchWords() = mainPageService.getHotSearch()

    companion object {

        private var instance: MainPageNetwork? = null

        fun getInstance(): MainPageNetwork =
            instance ?: synchronized(MainPageNetwork::class.java) {
                instance ?: MainPageNetwork().apply { instance = this }
            }

    }
}