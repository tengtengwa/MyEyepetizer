package com.example.base

import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.bean.Const

/**
 * 跳转各个模块的方法的服务类
 */
object StartService {
    fun startWeb(
        title: String,
        isTitleFixed: Boolean,
        url: String,
        titleTextSize: Int = Const.Web.TITLE_TEXT_SIZE_NORMAL
    ) {
        ARouter.getInstance()
            .build("/epetizer/webActivity")
            .withString("title", title)
            .withBoolean("isTitleFixed", isTitleFixed)
            .withString("url", url)
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