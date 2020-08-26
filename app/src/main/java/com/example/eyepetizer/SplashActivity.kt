package com.example.eyepetizer

import android.Manifest
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.example.base.BaseActivity
import com.example.base.StartService
import com.example.base.utils.SharedPreferenceUtils
import com.example.base.utils.toast
import com.gyf.immersionbar.ImmersionBar
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.app_activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 进入主页面前的“预加载”页面
 */
class SplashActivity : BaseActivity() {

    private val job by lazy { Job() }

    private val animationTime = 3000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = animationTime
            fillAfter = true        //动画效果结束后保持其状态
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f, 1.05f,      //x、y轴开始和结束动画时的倍数
            1f, 1.05f,
            //指定缩放动画的x、y轴的点，可以选择绝对坐标、关于自己的百分比或关于父view的百分比
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = animationTime
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_splash)
        if (isFirstLaunchApp) requestPermissions()
        isFirstLaunchApp = false
    }

    override fun setupViews() {
        //隐藏状态栏，其实就是给window设置了WindowManager.LayoutParams.FLAG_FULLSCREEN这个flag
        ImmersionBar.hideStatusBar(window)
//        iv_logo.animation = alphaAnimation
//        iv_bg.animation = scaleAnimation
//        alphaAnimation.start()
//        scaleAnimation.start()
        //上面代码可以替换为下面两行
        iv_logo.startAnimation(alphaAnimation)
        iv_bg.startAnimation(scaleAnimation)
        CoroutineScope(job).launch {
            delay(animationTime)
            StartService.startMain()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun requestPermissions() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
            )
            .explainReasonBeforeRequest()
            .request { allGranted, _, deniedList ->
                if (allGranted) {
                    "所有权限已允许".toast(this)
                } else {
                    "您拒绝了如下权限:$deniedList，建议手动开启".toast(this)
                }
            }
    }

    companion object {
        var isFirstLaunchApp: Boolean
            get() = SharedPreferenceUtils.readSP("isFirstLaunchApp", true)
            set(value) = SharedPreferenceUtils.writeSP("isFirstLaunchApp" to value)
    }
}