package com.example.base.bean

object Const {

    object Url {
        const val USER_AGREEMENT = "http://www.eyepetizer.net/agreement.html"
    }


    /**
     * web模块相关的常量
     */
    object Web {
        //加载模式：MODE_DEFAULT 默认使用WebView加载；
        // MODE_SONIC 使用VasSonic框架加载；
        // MODE_SONIC_WITH_OFFLINE_CACHE 使用VasSonic框架离线加载
        const val MODE_DEFAULT = 0

        const val MODE_SONIC = 1

        const val MODE_SONIC_WITH_OFFLINE_CACHE = 2

        //标题字体大小,正常为16sp，大字体为20sp，小字体为12sp
        const val TITLE_TEXT_SIZE_NORMAL = 3

        const val TITLE_TEXT_SIZE_BIG = 4

        const val TITLE_TEXT_SIZE_SMALL = 5
    }
}
