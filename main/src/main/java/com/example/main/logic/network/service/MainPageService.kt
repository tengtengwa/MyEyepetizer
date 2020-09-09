package com.example.main.logic.network.service

import com.example.main.logic.model.*
import retrofit2.http.GET
import retrofit2.http.Url

interface MainPageService {

    /**
     * 首页-发现列表
     */
    @GET
    suspend fun getHomeDiscovery(@Url url: String): Discovery

    /**
     * 首页-推荐列表
     */
    @GET
    suspend fun getHomePageRecommend(@Url url: String): HomePageRecommend

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun getHomeDaily(@Url url: String): Daily

    /**
     * 社区-推荐列表
     */
    @GET
    suspend fun getCommunityRecommend(@Url url: String): CommunityRecommend

    /**
     * 社区-关注列表
     */
    @GET
    suspend fun getCommunityFollow(@Url url: String): Follow

    /**
     * 通知-推送列表
     */
    @GET
    suspend fun getNotificationPush(@Url url: String): PushMessage

    /**
     * 搜索-热搜关键词
     */
    @GET("api/v3/queries/hot")
    suspend fun getHotSearch(): List<String>

    companion object {

        /**
         * 首页-发现列表
         */
        const val DISCOVERY_URL = "${ServiceCreator.BASE_URL}api/v7/index/tab/discovery"

        /**
         * 首页-推荐列表
         */
        const val HOMEPAGE_RECOMMEND_URL =
            "${ServiceCreator.BASE_URL}api/v5/index/tab/allRec?page=0"

        /**
         * 首页-日报列表
         */
        const val DAILY_URL = "${ServiceCreator.BASE_URL}api/v5/index/tab/feed"

        /**
         * 社区-推荐列表
         */
        const val COMMUNITY_RECOMMEND_URL = "${ServiceCreator.BASE_URL}api/v7/community/tab/rec"

        /**
         * 社区-关注列表
         */
        const val FOLLOW_URL = "${ServiceCreator.BASE_URL}api/v6/community/tab/follow"

        /**
         * 通知-推送列表
         */
        const val PUSHMESSAGE_URL = "${ServiceCreator.BASE_URL}api/v3/messages"
    }
}