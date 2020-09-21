package com.example.base.bean

object Const {

    object ItemViewType {

        const val UNKNOWN = -1              //未知类型，使用EmptyViewHolder容错处理。

        const val CUSTOM_HEADER = 0         //自定义头部类型。

        const val TEXT_CARD_HEADER1 = 1

        const val TEXT_CARD_HEADER2 = 2

        const val TEXT_CARD_HEADER3 = 3

        const val TEXT_CARD_HEADER4 = 4     //type:textCard -> dataType:TextCard,type:header4

        const val TEXT_CARD_HEADER5 = 5     //type:textCard -> dataType:TextCard -> type:header5

        const val TEXT_CARD_HEADER6 = 6

        const val TEXT_CARD_HEADER7 = 7    //type:textCard -> dataType:TextCardWithRightAndLeftTitle,type:header7

        const val TEXT_CARD_HEADER8 = 8    //type:textCard -> dataType:TextCardWithRightAndLeftTitle,type:header8

        const val TEXT_CARD_FOOTER1 = 9

        const val TEXT_CARD_FOOTER2 = 10    //type:textCard -> dataType:TextCard,type:footer2

        const val TEXT_CARD_FOOTER3 = 11    //type:textCard -> dataType:TextCardWithTagId,type:footer3

        const val BANNER = 12               //type:banner -> dataType:Banner

        const val BANNER3 = 13              //type:banner3-> dataType:Banner

        const val FOLLOW_CARD = 14          //type:followCard -> dataType:FollowCard -> type:video -> dataType:VideoBeanForClient

        const val TAG_BRIEFCARD = 15        //type:briefCard -> dataType:TagBriefCard

        const val TOPIC_BRIEFCARD = 16      //type:briefCard -> dataType:TopicBriefCard

        const val COLUMN_CARD_LIST = 17      //type:columnCardList -> dataType:ItemCollection

        const val VIDEO_SMALL_CARD = 18     //type:videoSmallCard -> dataType:VideoBeanForClient

        const val INFORMATION_CARD = 19     //type:informationCard -> dataType:InformationCard

        const val AUTO_PLAY_VIDEO_AD = 20   //type:autoPlayVideoAd -> dataType:AutoPlayVideoAdDetail

        const val HORIZONTAL_SCROLL_CARD = 21    //type:horizontalScrollCard -> dataType:HorizontalScrollCard

        const val SPECIAL_SQUARE_CARD_COLLECTION = 22   //type:specialSquareCardCollection -> dataType:ItemCollection

        const val UGC_SELECTED_CARD_COLLECTION = 23   //type:ugcSelectedCardCollection -> dataType:ItemCollection

        const val MAX = 100   //避免外部其他类型与此处包含的某个类型重复。
    }

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
