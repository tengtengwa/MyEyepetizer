package com.example.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.base.callback.RequestLifecycle
import com.example.base.utils.logD


/**
 * 所有Fragment的基类
 */
open class BaseFragment : Fragment(), RequestLifecycle {

    private var mHasLoadedData = false

    private var loadErrorView: View? = null

    /**
     * Fragment加载的根布局
     */
    protected var rootView: View? = null

    /**
     * Fragment中显示加载等待的控件。
     */
    protected var loadingView: View? = null

    /**
     * 此Fragment依附的Activity
     */
    lateinit var activity: Activity

    protected val TAG: String = this.javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 缓存当前依附的activity
        activity = requireActivity()
        logD(TAG, "BaseFragment-->onAttach()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseFragment-->onResume()")
        //当Fragment在屏幕上可见并且没有加载过数据时调用
        if (!mHasLoadedData) {
            loadDataOnce()
            logD(TAG, "BaseFragment-->loadDataOnce()")
            mHasLoadedData = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logD(TAG, "BaseFragment-->onDestroyView()")
        if (rootView?.parent != null) (rootView?.parent as ViewGroup).removeView(rootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseFragment-->onDestroy()")
    }

    /**
     * 开始加载，将加载等待控件显示。
     */
    @CallSuper
    override fun startLoading() {
        loadingView?.visibility = View.VISIBLE
        hideLoadErrorView()
    }

    /**
     * 加载完成，将加载等待控件隐藏。
     */
    @CallSuper
    override fun loadFinished() {
        loadingView?.visibility = View.GONE
    }

    /**
     * 加载失败，将加载等待控件隐藏。
     */
    @CallSuper
    override fun loadFailed(msg: String?) {
        loadingView?.visibility = View.GONE
    }

    /**
     * 在Fragment基类中获取通用的控件，会将传入的View实例原封不动返回。
     * @param view Fragment中inflate出来的View实例。
     * @return  Fragment中inflate出来的View实例原封不动返回。
     */
    fun onCreateView(view: View): View {
        logD(TAG, "BaseFragment-->onCreateView()")
        rootView = view
        loadingView = view.findViewById(R.id.loading)
        return view
    }

    /**
     * 页面首次可见时调用一次该方法，在这里可以请求网络数据等。
     */
    open fun loadDataOnce() {
    }

    /**
     * 当Fragment中的加载内容服务器返回失败或网络异常，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     * @param block 点击屏幕重新加载，回调处理。
     */
    protected fun showLoadErrorView(tip: String, block: View.() -> Unit) {
        if (loadErrorView != null) {
            loadErrorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.loadErrorView)
            if (viewStub != null) {
                loadErrorView = viewStub.inflate()
                val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
                loadErrorText?.text = tip
                val loadErrorkRootView = loadErrorView?.findViewById<View>(R.id.loadErrorkRootView)
                loadErrorkRootView?.setOnClickListener {
                    it?.block()
                }
            }
        }
    }

    /**
     * 将load error view进行隐藏。
     */
    protected fun hideLoadErrorView() {
        loadErrorView?.visibility = View.GONE
    }

/*    *//**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     *//*
    protected fun share(shareContent: String, shareType: Int) {
        ShareUtil.share(this.activity, shareContent, shareType)
    }

    *//**
     * 弹出分享对话框
     *
     * @param shareContent 分享内容
     *//*
    protected fun showDialogShare(shareContent: String) {
        if (this.activity is AppCompatActivity) {
            com.eyepetizer.android.extension.showDialogShare(this.activity as AppCompatActivity, shareContent)
        }
    }*/
}