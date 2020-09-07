package com.example.main.logic

/**
 * 主页面的仓库，用于管理首页、社区、通知和我的四个页面的数据请求
 */
class MainRepository {

    companion object {

        private var instance: MainRepository? = null

        // TODO: 2020/9/5 很多逻辑还没写，下面的构造方法还需要改
        fun getInstance(): MainRepository? =
            instance ?: synchronized(this) {
                instance ?: MainRepository().apply { instance = this }
            }

    }
}