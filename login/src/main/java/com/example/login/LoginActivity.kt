package com.example.login

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity
import com.example.base.StartService
import com.example.base.bean.Const
import com.example.base.customview.CustomFontTextView
import com.example.base.utils.setOnClickListener
import com.example.base.utils.toast
import kotlinx.android.synthetic.main.login_activity_login.*
import kotlinx.android.synthetic.main.login_activity_login.view.*
import kotlinx.android.synthetic.main.login_layout_title.*

@Route(path = "/epetizer/login")
class LoginActivity : BaseActivity() {

    private var isAuthorLoginClicked = false
    private var isUserRegisteClicked = false
    private lateinit var userRegiste: CustomFontTextView
    private lateinit var authorLogin: CustomFontTextView
    private lateinit var userRegisteTip: CustomFontTextView
    private lateinit var shareGroup: Group
    private lateinit var loginTip: CustomFontTextView
    private lateinit var findPassword: CustomFontTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_login)

    }

    override fun setupViews() {
        super.setupViews()
        setUpAllViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        setOnClickListener(
            tv_login,
            iv_share_wechat,
            iv_share_weibo,
            iv_share_qq,
            tv_right_top,
            tv_user_registe,
            tv_author_login,
            tv_agreement,
            iv_back
        ) {
            when (this) {
                tv_login -> {
                    "听说你想登陆？不存在的，伙计".toast(this@LoginActivity)
                }
                iv_share_wechat,
                iv_share_weibo,
                iv_share_qq -> {
                    "开发小哥偷了个懒~".toast(this@LoginActivity)
                }
                tv_right_top -> {
                    "先去登陆吧".toast(this@LoginActivity)
                }
                //下面两个TextView的点击效果中，必须使用findViewById获取对应View的引用，
                // 直接引用会找不到相应的View对象。暂不清楚问题的原因
                tv_user_registe -> {
                    if (isUserRegisteClicked) {
                        shareGroup.visibility = View.VISIBLE
                        userRegisteTip.visibility = View.INVISIBLE
                        userRegiste.text = resources.getString(R.string.login_user_registe)
                        isUserRegisteClicked = false
                    } else {
                        shareGroup.visibility = View.INVISIBLE
                        userRegisteTip.visibility = View.VISIBLE
                        userRegiste.text = resources.getString(R.string.login_user_login)
                        isUserRegisteClicked = true
                        if (isAuthorLoginClicked) {
                            shareGroup.visibility = View.INVISIBLE
                            findPassword.visibility = View.VISIBLE
                            authorLogin.text = resources.getString(R.string.login_author_login)
                            loginTip.text = resources.getString(R.string.login_login_tip)
                            isAuthorLoginClicked = false
                        }
                    }
                }
                tv_author_login -> {
                    if (isAuthorLoginClicked) {
                        shareGroup.visibility = View.VISIBLE
                        findPassword.visibility = View.VISIBLE
                        authorLogin.text = resources.getString(R.string.login_author_login)
                        loginTip.text = resources.getString(R.string.login_login_tip)
                        isAuthorLoginClicked = false
                    } else {
                        shareGroup.visibility = View.INVISIBLE
                        findPassword.visibility = View.INVISIBLE
                        authorLogin.text = resources.getString(R.string.login_user_login)
                        loginTip.text = resources.getString(R.string.login_auther_login_tip)
                        isAuthorLoginClicked = true
                        if (isUserRegisteClicked) {
                            userRegisteTip.visibility = View.INVISIBLE
                            userRegiste.text = resources.getString(R.string.login_user_registe)
                            isUserRegisteClicked = false
                        }
                    }
                }
                tv_agreement -> {
                    StartService.startWeb(
                        "开眼用户协议", true, Const.Url.USER_AGREEMENT)
                }
                iv_back -> finish()
            }
        }
    }

    private fun setUpAllViews() {
        userRegiste = findViewById(R.id.tv_user_registe)
        authorLogin = findViewById(R.id.tv_author_login)
        userRegisteTip = findViewById(R.id.tv_user_registe_tip)
        shareGroup = findViewById(R.id.gp_share)
        loginTip = findViewById(R.id.tv_login_tip)
        findPassword = findViewById(R.id.tv_right_top)

        setStatusBarBackground(R.color.base_colorBlack)
        layout_title.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        iv_back.setImageResource(R.drawable.login_ic_close_white_24dp)
        iv_share.visibility = View.INVISIBLE
        tv_right_top.visibility = View.VISIBLE
    }
}

