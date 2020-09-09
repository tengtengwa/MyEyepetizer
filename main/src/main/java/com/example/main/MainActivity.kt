package com.example.main

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.base.BaseActivity
import com.example.base.StartService
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.base.event.SwitchPageEvent
import com.example.base.utils.setOnClickListener
import com.example.base.utils.toastNotShow
import com.example.main.community.CommunityFragment
import com.example.main.home.HomeFragment
import com.example.main.home.recommend.RecommendFragment
import com.example.main.notification.NotificationFragment
import com.example.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.main_layout_bottom_nav.*
import org.greenrobot.eventbus.EventBus

@Route(path = "/epetizer/mainActivity")
class MainActivity : BaseActivity() {

    private lateinit var homeFragment: HomeFragment

    private lateinit var communityFragment: CommunityFragment

    private lateinit var notificationFragment: NotificationFragment

    private lateinit var profileFragment: ProfileFragment

    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)
    }

    override fun setupViews() {
        toast = "再按一次退出应用".toastNotShow(this)
        setOnClickListener(btn_community, btn_profile, btn_home, btn_notification, iv_release) {
            when (this) {
                btn_home -> {
                    notificationFragmentRefresh(HOME_PAGE)
                    replaceWithSpecificFragment(HOME_PAGE)
                }
                btn_community -> {
                    notificationFragmentRefresh(COMMUNITY_PAGE)
                    replaceWithSpecificFragment(COMMUNITY_PAGE)
                }
                btn_notification -> {
                    notificationFragmentRefresh(NOTIFICATION_PAGE)
                    replaceWithSpecificFragment(NOTIFICATION_PAGE)
                }
                btn_profile -> {
                    notificationFragmentRefresh(PROFILE_PAGE)
                    replaceWithSpecificFragment(PROFILE_PAGE)
                }
                iv_release -> StartService.startLogin()
            }
        }
    }

    private fun replaceWithSpecificFragment(fragmentNum: Int) {
        makeAllUnSelected()
        supportFragmentManager.beginTransaction().apply {
            hideAllFragments(this)
            when (fragmentNum) {
                HOME_PAGE -> {
                    iv_home.isSelected = true
                    tv_home.isSelected = true
                    //Fragment已经创建就直接show，否则创建新实例并add
                    if (::homeFragment.isInitialized) { //注意这种检查lateinit var变量是否初始化的写法
                        show(homeFragment)
                    } else {
                        homeFragment = HomeFragment.newInstance()
                        add(R.id.main_container, homeFragment)
                    }
                }
                COMMUNITY_PAGE -> {
                    iv_community.isSelected = true
                    tv_home.isSelected = true
                    if (::communityFragment.isInitialized) {
                        show(communityFragment)
                    } else {
                        communityFragment = CommunityFragment.newInstance()
                        add(R.id.main_container, communityFragment)
                    }
                }
                NOTIFICATION_PAGE -> {
                    iv_notification.isSelected = true
                    tv_notification.isSelected = true
                    if (::notificationFragment.isInitialized) {
                        show(notificationFragment)
                    } else {
                        notificationFragment = NotificationFragment.newInstance()
                        add(R.id.main_container, notificationFragment)
                    }
                }
                PROFILE_PAGE -> {
                    iv_profile.isSelected = true
                    tv_profile.isSelected = true
                    if (::profileFragment.isInitialized) {
                        show(profileFragment)
                    } else {
                        profileFragment = ProfileFragment.newInstance()
                        add(R.id.main_container, profileFragment)
                    }
                }
            }
        }.commitAllowingStateLoss()
    }

    private fun makeAllUnSelected() {
        iv_home.isSelected = false
        tv_home.isSelected = false
        iv_community.isSelected = false
        tv_home.isSelected = false
        iv_notification.isSelected = false
        tv_notification.isSelected = false
        iv_profile.isSelected = false
        tv_profile.isSelected = false
    }

    private fun hideAllFragments(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.apply {
            hide(homeFragment)
            hide(communityFragment)
            hide(notificationFragment)
            hide(profileFragment)
        }
    }

    private fun notificationFragmentRefresh(fragmentNum: Int) {
        when (fragmentNum) {
            HOME_PAGE -> if (iv_home.isSelected) {
                EventBus.getDefault().post(RefreshEvent(HomeFragment::class.java))
            }
            COMMUNITY_PAGE -> if (iv_community.isSelected) {
                EventBus.getDefault().post(RefreshEvent(CommunityFragment::class.java))
            }
            NOTIFICATION_PAGE -> if (iv_notification.isSelected) {
                EventBus.getDefault().post(RefreshEvent(NotificationFragment::class.java))
            }
            PROFILE_PAGE -> if (iv_profile.isSelected) {
                EventBus.getDefault().post(RefreshEvent(ProfileFragment::class.java))
            }
        }

    }

    override fun onHandleEvent(event: MessageEvent) {
        super.onHandleEvent(event)
        when {
            event is SwitchPageEvent && event.clazz == RecommendFragment::class.java -> {

            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            if (toast.view.parent == null) {
                toast.show()
            } else {
                finish()
            }
        }
    }

    companion object {
        /**
         * 上面几个方法会用到下面的常量来判断是那个Fragment要进行刷新、显示等操作
         */
        const val HOME_PAGE = 0
        const val COMMUNITY_PAGE = 1
        const val NOTIFICATION_PAGE = 2
        const val PROFILE_PAGE = 3
    }
}