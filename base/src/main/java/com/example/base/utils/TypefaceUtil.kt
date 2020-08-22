package com.example.base.utils

import android.graphics.Typeface
import com.example.base.MyApplication

object TypefaceUtil {

    const val CONDENSED_BOLD = 4

    const val CONDENSED_MEDIUM = 3

    const val FZLANTINGHEI_DB1 = 2

    const val FZLANTINGHEI_L = 1

    const val LOBSTER = 5

    private var boldTypeface: Typeface? = null

    private var mediumTypeface: Typeface? = null

    private var db1Typeface: Typeface? = null

    private var lTypeface: Typeface? = null

    private var lobsterTypeface: Typeface? = null

    fun getTypeface(type: Int): Typeface = when (type) {
        CONDENSED_BOLD -> boldTypeface ?: getTypeface("fonts/DIN-Condensed-Bold.ttf")
        CONDENSED_MEDIUM -> mediumTypeface ?: getTypeface("fonts/Futura-CondensedMedium.ttf")
        FZLANTINGHEI_DB1 -> db1Typeface ?: getTypeface("fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        FZLANTINGHEI_L -> lTypeface ?: getTypeface("fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        LOBSTER -> lobsterTypeface ?: getTypeface("fonts/Lobster-1.4.otf")
        else -> Typeface.DEFAULT
    }

    private fun getTypeface(path: String) = try {
        Typeface.createFromAsset(MyApplication.context.assets, path)
    } catch (e: RuntimeException) {
        logD("typeface", "加载字体文件失败")
        Typeface.DEFAULT
    }
}