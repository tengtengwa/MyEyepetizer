package com.example.base.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.base.R

class TypefaceEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        attrs?.let {
            val array = context.obtainStyledAttributes(it, R.styleable.TypefaceEditText, 0, 0)
            val typeface = array.getInt(R.styleable.TypefaceTextView_typeface, 0)
            setTypeface(TypefaceTextView.getTypeface(typeface))
            array.recycle()
        }
    }
}