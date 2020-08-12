package com.example.login

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.base.BaseActivity
import com.example.base.utils.logD
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_title.*

@Route(path = "/epetizer/loginActivity")
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun setupViews() {
        super.setupViews()
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

