package com.example.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity
import com.example.base.StartService
import com.example.base.utils.setOnClickListener
import com.example.main.community.CommunityFragment
import com.example.main.home.HomeFragment
import com.example.main.notification.NotificationFragment
import com.example.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.main_layout_bottom_nav.*

@Route(path = "/epetizer/mainActivity")
class MainActivity : BaseActivity() {

    private lateinit var homeFragment: HomeFragment

    private lateinit var communityFragment: CommunityFragment

    private lateinit var notificationFragment: NotificationFragment

    private lateinit var profileFragment: ProfileFragment

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)
    }

    override fun setupViews() {
        initParams()
        setOnClickListener(btn_community, btn_profile, btn_home, btn_notification, iv_release) {
            when (this) {
                btn_community -> viewModel.isRefreshCommunityPage.value = true
                btn_profile -> viewModel.isRefreshProfilePage.value = true
                btn_home -> viewModel.isRefreshHomePage.value = true
                btn_notification -> viewModel.isRefreshNotificationPage.value = true
                iv_release -> StartService.startLogin()
            }
        }
    }

    private fun initParams() {
        homeFragment = HomeFragment.newInstance()
        communityFragment = CommunityFragment.newInstance()
        notificationFragment = NotificationFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}