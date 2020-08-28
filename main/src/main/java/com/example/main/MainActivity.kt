package com.example.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity
import com.example.base.StartService
import com.example.base.bean.Const
import com.example.base.utils.setOnClickListener
import com.example.main.profile.CacheActivity
import com.example.main.settings.SettingsActivity
import kotlinx.android.synthetic.main.main_fragment_profile.*

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