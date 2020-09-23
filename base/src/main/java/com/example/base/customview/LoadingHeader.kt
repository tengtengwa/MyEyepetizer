package com.example.base.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.example.base.R
import com.example.base.utils.DensityUtil
import com.example.base.utils.logD
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.simple.SimpleComponent
import kotlinx.android.synthetic.main.base_layout_srl_common_header.view.*

/**
 * 自定义SmartRefreshLayout的刷新加载头
 */
class LoadingHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SimpleComponent(context, attrs, defStyleAttr), RefreshHeader {

    private var thisView = View.inflate(context, R.layout.base_layout_srl_common_header, this)

    private var loadingView = iv_loadingview

    private var radius = 20f        //箭头所在圆的半径

    private var millisPerCircle = 1000L   //箭头转一圈所需时间

    private val minHeight = 50f     //视图默认最小高度

    private var mState: RefreshState = RefreshState.None       //当前状态

    private var mFinished = false

    private var rotateAnimation: RotateAnimation

    init {
        context.obtainStyledAttributes(attrs, R.styleable.LoadingHeader).apply {
            radius = getFloat(R.styleable.LoadingHeader_radius, 50f)
            millisPerCircle = getInteger(R.styleable.LoadingHeader_millisPerCircle, 1000).toLong()
        }.recycle()
        thisView.apply {
            minimumHeight = DensityUtil.dp2px(minHeight)
        }
        rotateAnimation = RotateAnimation(
            0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            repeatMode = Animation.START_ON_FIRST_FRAME
            repeatCount = Animation.INFINITE
            duration = millisPerCircle
            interpolator = LinearInterpolator()
        }
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        mState = newState
        logD("mstate", "$newState")
        when (newState) {
            RefreshState.None, RefreshState.PullDownToRefresh -> {
                mFinished = false
            }
            else -> {
            }
        }
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
        if (mState == RefreshState.Refreshing) {
            return
        }
        if (isDragging && !mFinished) {
            val originalDragPercent = 1f * offset / height
            loadingView.apply {
                rotation = originalDragPercent * 360
                invalidate()
            }
        }
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        loadingView.apply {
            animation = rotateAnimation
            startAnimation(rotateAnimation)
        }
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        loadingView.apply {
            clearAnimation()
            translationY = 0F
        }
        mFinished = true
        return 0
    }

    override fun getView() = this

    override fun getSpinnerStyle(): SpinnerStyle = SpinnerStyle.Translate

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {}

    override fun isSupportHorizontalDrag() = false
}