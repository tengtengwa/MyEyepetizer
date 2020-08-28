package com.example.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.base.StartService
import com.example.base.bean.Const
import com.example.base.utils.setOnClickListener
import com.example.main.R
import com.example.main.profile.settings.SettingsActivity
import kotlinx.android.synthetic.main.main_fragment_profile.*


/**
 * 主页面中“我的”对应的Fragment
 */
class ProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListener(
            iv_settings, iv_logo, tv_tip, tv_favorites, tv_cache, tv_my_follow, tv_watch_history,
            tv_notification_toggle, tv_my_badge, tv_feedback, tv_contribution
        ) {
            when (this) {
                iv_settings -> SettingsActivity.start(hostActivity)
                iv_logo, tv_tip, tv_favorites, tv_my_follow, tv_watch_history,
                tv_notification_toggle, tv_my_badge -> {
                    StartService.startLogin()
                }
                tv_feedback -> StartService.startWeb(
                    "意见反馈",
                    true,
                    "http://data.kaiyanapp.com/api/vtrack/config?project=default",
                    Const.Web.TITLE_TEXT_SIZE_BIG
                )
                tv_contribution -> StartService.startWeb(
                    "成为作者",
                    true,
                    "http://open.eyepetizer.net/",
                    Const.Web.TITLE_TEXT_SIZE_BIG
                )
                tv_cache -> CacheActivity.start(hostActivity)
            }
        }
    }

    companion object {
        /**
         * 使用工厂方法创建实例
         */
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}