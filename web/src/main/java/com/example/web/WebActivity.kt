package com.example.web

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.webkit.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.BaseActivity
import com.example.base.bean.Const.Web.MODE_DEFAULT
import com.example.base.bean.Const.Web.MODE_SONIC_WITH_OFFLINE_CACHE
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_BIG
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_NORMAL
import com.example.base.bean.Const.Web.TITLE_TEXT_SIZE_SMALL
import com.example.base.utils.logD
import com.example.web.vassonic.SonicRuntimeImpl
import com.eyepetizer.android.ui.common.ui.vassonic.OfflinePkgSessionConnection
import com.eyepetizer.android.ui.common.ui.vassonic.SonicSessionClientImpl
import com.tencent.sonic.sdk.*
import kotlinx.android.synthetic.main.web_activity_web.*
import kotlinx.android.synthetic.main.web_layout_title.*

@Route(path = "/epetizer/web")
class WebActivity : BaseActivity() {

    @Autowired
    private var title: String = ""

    @Autowired
    private var url: String = "https://github.com/VIPyinzhiwei/Eyepetizer"

    @Autowired
    private var isTitleFixed: Boolean = false

    @Autowired
    private var loadMode: Int = MODE_DEFAULT

    @Autowired
    private var titleTextSize = TITLE_TEXT_SIZE_NORMAL

    private var sonicSession: SonicSession? = null

    private var sonicSessionClient: SonicSessionClientImpl? = null

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
        preloadInitVasSonic()
        if (sonicSessionClient != null) {
            sonicSessionClient?.bindWebView(webview)
            sonicSessionClient?.clientReady()
        } else {
            webview.loadUrl(url)
        }
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

    private fun preloadInitVasSonic() {
        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        // init sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(SonicRuntimeImpl(application), SonicConfig.Builder().build())
        }

        // if it's sonic mode , startup sonic session at first time
        if (MODE_DEFAULT != loadMode) { // sonic mode
            val sessionConfigBuilder = SonicSessionConfig.Builder()
            sessionConfigBuilder.setSupportLocalServer(true)

            // if it's offline pkg mode, we need to intercept the session connection
            if (MODE_SONIC_WITH_OFFLINE_CACHE == loadMode) {
                sessionConfigBuilder.setCacheInterceptor(object : SonicCacheInterceptor(null) {
                    override fun getCacheData(session: SonicSession): String? {
                        return null // offline pkg does not need cache
                    }
                })
                sessionConfigBuilder.setConnectionInterceptor(object :
                    SonicSessionConnectionInterceptor() {
                    override fun getConnection(
                        session: SonicSession,
                        intent: Intent
                    ): SonicSessionConnection {
                        return OfflinePkgSessionConnection(this@WebActivity, session, intent)
                    }
                })
            }

            // create sonic session and run sonic flow
            sonicSession =
                SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build())
            if (null != sonicSession) {
                sonicSession?.bindClient(SonicSessionClientImpl().also { sonicSessionClient = it })
            } else {
                // this only happen when a same sonic session is already running,
                // u can comment following codes to feedback as a default mode.
                // throw new UnknownError("create session fail!");
                logD(TAG, "${title},${url}:create sonic session fail!")
            }
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
            sonicSession?.sessionClient?.pageFinish(url)
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

        //vassonic根据情况拦截网页加载
        override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
            if (sonicSession != null) {
                val requestResponse = sonicSessionClient?.requestResource(url)
                if (requestResponse is WebResourceResponse) return requestResponse
            }
            return null
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
        sonicSession?.destroy()
        sonicSession = null
        super.onDestroy()
    }
}
