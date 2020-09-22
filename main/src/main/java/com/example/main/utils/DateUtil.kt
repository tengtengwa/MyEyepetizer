/*
 * Copyright (C) guolin, Suzhou Quxiang Inc. Open source codes for study only.
 * Do not use for commercial purpose.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.main.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间和日期工具类。
 *
 * @author guolin
 * @since 17/6/30
 */
object DateUtil {

    private const val MINUTE = (60 * 1000).toLong()

    private const val HOUR = 60 * MINUTE

    private const val DAY = 24 * HOUR

    private const val WEEK = 7 * DAY

    private const val MONTH = 4 * WEEK

    private const val YEAR = 365 * DAY

    /**
     * 根据传入的Unix时间戳，获取转换过后更加易读的时间格式。
     * @param dateMillis
     * Unix时间戳
     * @return 转换过后的时间格式，如2分钟前，1小时前。
     */
    fun getConvertedDate(dateMillis: Long): String {
        val currentMillis = System.currentTimeMillis()
        val timePast = currentMillis - dateMillis
        if (timePast > -MINUTE) { // 采用误差一分钟以内的算法，防止客户端和服务器时间不同步导致的显示问题
            when {
                /*timePast < HOUR -> {
                    var pastMinutes = timePast / MINUTE
                    if (pastMinutes <= 0) {
                        pastMinutes = 1
                    }
                    return pastMinutes.toString() + GlobalUtil.getString(R.string.minutes_ago)
                }*/
                timePast < DAY -> {
                    var pastHours = timePast / HOUR
                    if (pastHours <= 0) {
                        pastHours = 1
                    }
                    /*return pastHours.toString() + GlobalUtil.getString(R.string.hours_ago)*/
                    return getDateAndHourMinuteTime(dateMillis)
                }
                timePast < WEEK -> {
                    var pastDays = timePast / DAY
                    if (pastDays <= 0) {
                        pastDays = 1
                    }
                    return "$pastDays 天前"
                }
                timePast < MONTH -> {
                    var pastDays = timePast / WEEK
                    if (pastDays <= 0) {
                        pastDays = 1
                    }
                    return "$pastDays 周前"
                }
                else -> return getDate(dateMillis)
            }
        } else {
            return getDateAndTime(dateMillis)
        }
    }

    fun isBlockedForever(timeLeft: Long) = timeLeft > 5 * YEAR

    fun getDate(dateMillis: Long, pattern: String = "yyyy-MM-dd"): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(Date(dateMillis))
    }

    private fun getDateAndTime(dateMillis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(Date(dateMillis))
    }

    private fun getDateAndHourMinuteTime(dateMillis: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date(dateMillis))
    }


    /**
     * 转换视频时长的工具函数，我们假设没有超过99分钟的视频【滑稽】
     * @param sec 转换前视频的时长，单位：秒
     */
    fun convertVideoDuration(sec: Int): String {
        val minute = sec % 60
        val second = sec - minute * 60
        return if (minute >= 10) "$minute:$second" else "0$minute:$second"
    }
}
