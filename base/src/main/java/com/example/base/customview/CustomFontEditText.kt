package com.example.base.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.base.R

@SuppressLint("CustomViewStyleable")
class CustomFontEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        attrs?.let {
            val array = context.obtainStyledAttributes(it, R.styleable.CustomFontTextView, 0, 0)
            val typefaceType = array.getInt(R.styleable.CustomFontTextView_typeface, 0)
            typeface = CustomFontTextView.getTypeface(typefaceType)
            array.recycle()
        }
    }
}