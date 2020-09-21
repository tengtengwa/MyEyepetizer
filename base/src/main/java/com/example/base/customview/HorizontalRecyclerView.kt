package com.example.base.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * 处理了滑动冲突的嵌套RecyclerView的内部横向的RecyclerView
 */
class HorizontalRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var mLastX = 0f

    private var mLastY = 0f

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val curX = ev.x
        val curY = ev.y
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(curX - mLastX) < abs(curY - mLastY)) {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            else -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}