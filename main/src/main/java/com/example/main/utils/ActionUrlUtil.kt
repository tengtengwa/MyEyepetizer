/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.main.utils

import com.example.base.StartService
import com.example.base.bean.Const
import com.example.base.utils.GlobalUtil
import com.example.base.utils.toast
import com.example.main.R
import java.net.URLDecoder

/**
 * actionUrl事件处理工具类。通过截取actionUrl相关信息，并进行相应事件处理。
 * 根据自己项目的情况进行了一些修改
 */
object ActionUrlUtil {

//    /**
//     * 处理ActionUrl事件。
//     *
//     * @param fragment 上下文环境
//     * @param actionUrl 待处理的url
//     * @param toastTitle toast提示标题 or 没有匹配的事件需要处理，给出的提示标题。
//     */
//    fun process(fragment: BaseFragment, actionUrl: String?, toastTitle: String = "") {
//        process(actionUrl, toastTitle)
//    }

    /**
     * 处理ActionUrl事件。
     *
     * @param actionUrl 待处理的url
     * @param toastTitle toast提示标题 or 没有匹配的事件需要处理，给出的提示标题。
     */
    fun process(actionUrl: String?, toastTitle: String = "") {
        if (actionUrl == null) return
        val decodeUrl = URLDecoder.decode(actionUrl, "UTF-8")
        when {
            decodeUrl.startsWith(Const.ActionUrl.WEBVIEW) -> {
                StartService.startWeb(
                    decodeUrl.getWebViewInfo().first(),
                    true,
                    decodeUrl.getWebViewInfo().last()
                )
            }
            decodeUrl == Const.ActionUrl.RANKLIST -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            decodeUrl.startsWith(Const.ActionUrl.TAG) -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            decodeUrl == Const.ActionUrl.HP_SEL_TAB_TWO_NEWTAB_MINUS_THREE -> {
//                MainViewModel.switchPage(DailyFragment::class.java)
            }
            decodeUrl == Const.ActionUrl.CM_TAGSQUARE_TAB_ZERO -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            decodeUrl == Const.ActionUrl.CM_TOPIC_SQUARE -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            decodeUrl == Const.ActionUrl.CM_TOPIC_SQUARE_TAB_ZERO -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            decodeUrl.startsWith(Const.ActionUrl.COMMON_TITLE) -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            actionUrl == Const.ActionUrl.HP_NOTIFI_TAB_ZERO -> {
//                MainViewModel.apply {
//                    switchPage(PushFragment::class.java)
//                    refreshPage(PushFragment::class.java)
//                }
            }
            actionUrl.startsWith(Const.ActionUrl.TOPIC_DETAIL) -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
            actionUrl.startsWith(Const.ActionUrl.DETAIL) -> {
                TODO("此Activity和跳转待完成")
//                getConversionVideoId(actionUrl)?.run { NewDetailActivity.start(activity, this) }
            }
            else -> {
                "${toastTitle},${GlobalUtil.getString(R.string.main_not_support_yet)}".toast()
            }
        }
    }

    /**
     * 截取标题与url信息。
     *
     * @return first=标题 last=url
     */
    private fun String.getWebViewInfo(): Array<String> {
        val title = this.split("title=").last().split("&url").first()
        val url = this.split("url=").last()
        return arrayOf(title, url)
    }

    /**
     *  截取视频id。
     *
     *  @param actionUrl 解码后的actionUrl
     *  @return 视频id
     */
    private fun getConversionVideoId(actionUrl: String?): Long? {
        return try {
            val list = actionUrl?.split(Const.ActionUrl.DETAIL)
            list!![1].toLong()
        } catch (e: Exception) {
            null
        }
    }
}