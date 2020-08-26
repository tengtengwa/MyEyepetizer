package com.example.base

import com.alibaba.android.arouter.launcher.ARouter

/**
 * 跳转各个模块的方法的服务类
 */
object StartService {
    fun startWeb(
        title: String,
        isTitleFixed: Boolean,
        url: String,
        mode: Int,
        titleTextSize: Int
    ) {
        ARouter.getInstance()
            .build("/epetizer/webActivity")
            .withString("title", title)
            .withBoolean("isTitleFixed", isTitleFixed)
            .withString("url", url)
            .withInt("loadMode", mode)
            .withInt("titleTextSize", titleTextSize)
            .navigation()
    }

    fun startLogin() {
        ARouter.getInstance().build("/epetizer/loginActivity").navigation()
    }

    fun startMain() {
        ARouter.getInstance().build("/epetizer/mainActivity").navigation()
    }
}