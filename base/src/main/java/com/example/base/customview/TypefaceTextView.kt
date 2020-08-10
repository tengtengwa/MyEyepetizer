package com.example.base.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.base.R
import com.example.base.utils.TypefaceUtil

class TypefaceTextView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        //使用自定义TypefaceTextView就是为了更换字体，所以基本上都会设置typeface这个自定义属性，感觉这个判空有点多余...
        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView, 0, 0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface, 0)
            typeface = getTypeface(typefaceType)
            typedArray.recycle()
        }
    }

    private fun getTypeface(typefaceType: Int): Typeface? = when (typefaceType) {
        TypefaceUtil.CONDENSED_BOLD -> TypefaceUtil.getTypeface(TypefaceUtil.CONDENSED_BOLD)
        TypefaceUtil.CONDENSED_MEDIUM -> TypefaceUtil.getTypeface(TypefaceUtil.CONDENSED_MEDIUM)
        TypefaceUtil.FZLANTINGHEI_DB1 -> TypefaceUtil.getTypeface(TypefaceUtil.FZLANTINGHEI_DB1)
        TypefaceUtil.FZLANTINGHEI_L -> TypefaceUtil.getTypeface(TypefaceUtil.FZLANTINGHEI_L)
        TypefaceUtil.LOBSTER -> TypefaceUtil.getTypeface(TypefaceUtil.LOBSTER)
        else -> Typeface.DEFAULT
    }
}