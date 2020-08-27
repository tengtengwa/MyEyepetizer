package com.example.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity

@Route(path = "/epetizer/mainActivity")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)

    }

    override fun setupViews() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}