package com.example.base

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.customview.LoadingFooter
import com.example.base.customview.LoadingHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


class MyApplication : Application() {

    init {
        context = this
        SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
            layout.apply {
                setEnableLoadMore(true)
                setEnableLoadMoreWhenContentNotFull(true)
                setEnableOverScrollDrag(false)
            }
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            LoadingHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.apply {
                setRefreshFooter(LoadingFooter(context))
                setEnableFooterFollowWhenNoMoreData(true)
                setEnableFooterTranslationContent(true)
                setFooterTriggerRate(0.6f)
            }
            LoadingFooter(context).apply {
                setNoMoreDataText("- The End -")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    companion object {
        lateinit var context: Context

        private fun isDebug(): Boolean {
            return try {
                val info = context.applicationInfo
                (info.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
            } catch (e: Exception) {
                false
            }
        }
    }
}