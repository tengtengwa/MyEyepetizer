package com.example.web

import android.graphics.Bitmap
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.webkit.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_BIG
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_NORMAL
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_SMALL
import kotlinx.android.synthetic.main.web_activity_web.*
import kotlinx.android.synthetic.main.web_layout_title.*

@Route(path = "/epetizer/webActivity")
class WebActivity : BaseActivity() {

    @Autowired
    private var title: String = ""

    @Autowired
    private var url: String = "https://github.com/VIPyinzhiwei/Eyepetizer"

    @Autowired
    private var isTitleFixed: Boolean = false

    @Autowired
    private var titleTextSize = TITLE_TEXT_SIZE_NORMAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity_web)
        /**
         * 这里集成开发模式的时候再使用依赖注入，单独开发的时候会报错
         */
//        ARouter.getInstance().inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        initParams()
        initWebView()
        webview.loadUrl(url)
    }

    private fun initParams() {
        tv_title.text = title
        tv_title.apply {
            when (titleTextSize) {
                TITLE_TEXT_SIZE_BIG -> setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    20F         //这里很奇怪，不能使用dimen资源文件中定义的float类型的大小
                )
                TITLE_TEXT_SIZE_SMALL -> setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    12F
                )
                else -> setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    16F
                )
            }
        }
        iv_share.visibility = View.INVISIBLE
        tv_title.isSelected = true          //想要实现跑马灯效果，它必须获得焦点
        setStatusBarBackground(R.color.base_colorBlack)
    }


    private fun initWebView() {
        webview.settings.apply {
            javaScriptEnabled = true
            //设置自适应屏幕，两者合用
            useWideViewPort = true          //将图片调整到适合webview的大小
            loadWithOverviewMode = true     // 缩放至屏幕的大小
            javaScriptCanOpenWindowsAutomatically = true    //支持通过JS打开新窗口
            loadsImagesAutomatically = true                 //支持自动加载图片
            setAppCacheEnabled(true)
            savePassword = false
            saveFormData = false
        }
        webview.apply {
            //移除对指定的javascript接口的调用,防止webview远程代码执行漏洞
            removeJavascriptInterface("searchBoxJavaBridge_")
            removeJavascriptInterface("accessibility")
            removeJavascriptInterface("accessibilityTraversal")

            webChromeClient = MyWebChromeClient()
            webViewClient = MyWebViewClient()
        }
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            loadingview.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            loadingview.visibility = View.INVISIBLE
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            tv_load_error_tip.visibility = View.VISIBLE
            loadingview.visibility = View.INVISIBLE
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressbar.progress = newProgress
        }

        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            if (!isTitleFixed) {
                if (title != null) {
                    this@WebActivity.title = title
                }
                tv_title.text = title
            }
        }
    }

    override fun onDestroy() {
        //下面主要是为了防止webview内存泄漏
        webview?.let {
            it.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            it.clearHistory()
            container.removeView(it)
            it.destroy()
        }
        super.onDestroy()
    }
}
