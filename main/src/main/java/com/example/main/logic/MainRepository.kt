package com.example.main.logic

import com.example.main.logic.dao.MainPageDao
import com.example.main.logic.network.MainPageNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 主页面的仓库，用于管理首页、社区、通知和我的四个页面的数据请求
 */
class MainRepository private constructor(
    private val mainNetwork: MainPageNetwork,
    private val mainDao: MainPageDao
) {

    suspend fun refreshHomeDiscovery(url: String) = requestHomeDiscovery(url)

    suspend fun refreshHomeRecommend(url: String) = requestHomeRecommend(url)

    suspend fun refreshHomeDaily(url: String) = requestHomeDaily(url)

    suspend fun refreshCommunityRecommend(url: String) = requestCommunityRecommend(url)

    suspend fun refreshCommunityFollow(url: String) = requestCommunityFollow(url)

    suspend fun refreshNotificationPush(url: String) = requestNotificationPush(url)

    private suspend fun requestHomeDiscovery(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchHomeDiscovery(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    private suspend fun requestHomeRecommend(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchHomeRecommend(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    private suspend fun requestHomeDaily(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchHomeDaily(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    private suspend fun requestCommunityRecommend(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchCommunityRecommend(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    private suspend fun requestCommunityFollow(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchCommunityFollow(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    private suspend fun requestNotificationPush(url: String) = withContext(Dispatchers.IO) {
        val response = mainNetwork.fetchNotificationPush(url)
        //TODO("在这里通过mainDao缓存数据，也可以在上一行代码前获取Dao层的缓存")
        response
    }

    companion object {

        private var instance: MainRepository? = null

        fun getInstance(mainNetwork: MainPageNetwork, mainDao: MainPageDao): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(mainNetwork, mainDao).apply { instance = this }
            }
    }
}