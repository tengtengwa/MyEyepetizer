package com.example.login

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.BaseActivity
import com.example.base.utils.setOnClickListener
import com.example.base.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tv_login
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.layout_title.*

@Route(path = "/epetizer/loginActivity")
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
            tv_user_register,
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
                }
                tv_user_register -> {
                }
                tv_author_login -> {
                }
                tv_agreement -> {
                }
                iv_back -> finish()
            }
        }
    }

    private fun setUpAllViews() {
        setStatusBarBackground(R.color.colorBlack)
        layout_title.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        iv_back.setImageResource(R.drawable.ic_close_white_24dp)
        iv_share.visibility = View.INVISIBLE
        tv_right_top.visibility = View.VISIBLE
    }


    companion object {
        fun start() {
            ARouter.getInstance().build("/epetizer/loginActivity").navigation()
        }
    }
}

