package com.example.base.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import com.blankj.utilcode.utils.ConvertUtils
import com.example.base.R
import com.example.base.utils.DensityUtil
import com.example.base.utils.TypefaceUtil
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.simple.SimpleComponent
import kotlinx.android.synthetic.main.base_layout_srl_classics_footer.view.*

class LoadingFooter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttrs: Int = 0
) : SimpleComponent(context, attrs, defAttrs), RefreshFooter {

    private var tipText: String? = null

    private var textSize = 16f

    private var textView: TextView

    //footer默认高度(px)，在Application中全局设置无效，暂不清楚原因，因此在这里手动设置
    private var mFooterHeight = 459

    private var mRefreshKernel: RefreshKernel? = null

    private var mNoMoreData = false

    init {
        View.inflate(context, R.layout.base_layout_srl_classics_footer, this)
        textView = srl_classics_title
        context.obtainStyledAttributes(R.styleable.LoadingFooter).apply {
            textView.text = when {
                hasValue(R.styleable.LoadingFooter_tipText) -> {
                    getString(R.styleable.LoadingFooter_tipText)
                        ?: context.getString(R.string.base_srl_footer_nothing)
                }
                tipText != null -> {
                    tipText
                }
                else -> {
                    context.getString(R.string.base_srl_footer_nothing)
                }
            }
            if (hasValue(R.styleable.LoadingFooter_android_textSize)) {
                textView.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, getDimensionPixelSize(
                        R.styleable.LoadingFooter_android_textSize, ConvertUtils.dp2px(textSize)
                    ).toFloat()
                )
            }
            val typeface = CustomFontTextView.getTypeface(
                getInt(R.styleable.LoadingFooter_srlTextTypeface, TypefaceUtil.LOBSTER)
            )
            textView.typeface = typeface
        }.recycle()
    }

    override fun getView() = this

    override fun onInitialized(@NonNull kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
        mRefreshKernel = kernel
        mRefreshKernel?.requestDrawBackgroundFor(this, Color.WHITE)
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        super.onFinish(refreshLayout, success)
        return 0
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        super.onStateChanged(refreshLayout, oldState, newState)
        if (!mNoMoreData) {
            when (newState) {
                RefreshState.PullUpToLoad -> {
                    refreshFooterHeight()
                }
                else -> {
                }
            }
        } else {
            refreshFooterHeight()
        }
    }

    /**
     * 将mNoMoreData标记位置为true，表示已经没有数据了，上拉不再进行加载
     */
    override fun setNoMoreData(noMoreData: Boolean): Boolean {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData
            refreshFooterHeight()
            if (noMoreData) {
                textView.text = tipText
            }
        }
        return true
    }

    //没有数据时设置最大高度为mFooterHeight，否则高度为0
    private fun refreshFooterHeight() {
        if (mNoMoreData) {
            mRefreshKernel?.refreshLayout?.setFooterHeightPx(mFooterHeight)
        } else {
            mRefreshKernel?.refreshLayout?.setFooterHeight(0f)
        }
        mRefreshKernel?.requestRemeasureHeightFor(this)
    }

    fun setNoMoreDataText(text: String) {
        tipText = text
    }

    fun setFooterHeight(dpValue: Float) {
        mFooterHeight = DensityUtil.dp2px(dpValue)
    }
}