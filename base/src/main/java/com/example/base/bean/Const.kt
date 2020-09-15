package com.example.base.bean

object Const {

    object Url {
        const val USER_AGREEMENT = "http://www.eyepetizer.net/agreement.html"
    }

    /**
     * web模块相关的常量
     */
    object Web {
        //标题字体大小,正常为16sp，大字体为20sp，小字体为12sp
        const val TITLE_TEXT_SIZE_NORMAL = 3

        const val TITLE_TEXT_SIZE_BIG = 4

        const val TITLE_TEXT_SIZE_SMALL = 5
    }

    object ActionUrl {
        const val TAG = "eyepetizer://tag/"

        const val DETAIL = "eyepetizer://detail/"

        const val RANKLIST = "eyepetizer://ranklist/"

        const val WEBVIEW = "eyepetizer://webview/?title="

        const val REPLIES_HOT = "eyepetizer://replies/hot?"

        const val TOPIC_DETAIL = "eyepetizer://topic/detail?"

        const val COMMON_TITLE = "eyepetizer://common/?title"

        const val LT_DETAIL = "eyepetizer://lightTopic/detail/"

        const val HP_NOTIFI_TAB_ZERO = "eyepetizer://homepage/notification?tabIndex=0"

        const val HP_SEL_TAB_TWO_NEWTAB_MINUS_THREE =
            "eyepetizer://homepage/selected?tabIndex=2&newTabIndex=-3"

        const val CM_TOPIC_SQUARE = "eyepetizer://community/topicSquare"

        const val CM_TAGSQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"

        const val CM_TOPIC_SQUARE_TAB_ZERO = "eyepetizer://community/tagSquare?tabIndex=0"
    }
}
