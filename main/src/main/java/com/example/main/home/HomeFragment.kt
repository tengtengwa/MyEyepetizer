package com.example.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.base.event.SwitchPageEvent
import com.example.main.BaseViewPagerFragment
import com.example.main.R
import com.example.main.home.daily.DailyFragment
import com.example.main.home.discovery.DiscoveryFragment
import com.example.main.home.recommend.RecommendFragment
import kotlinx.android.synthetic.main.main_fragment_home.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*
import org.greenrobot.eventbus.EventBus

/**
 * 主页面中含有ViewPager的HomeFragment
 */
class HomeFragment : BaseViewPagerFragment() {

    override val tabTitles: Array<String> = arrayOf(
        resources.getString(R.string.main_home_tab1),
        resources.getString(R.string.main_home_tab2),
        resources.getString(R.string.main_home_tab3)
    )

    override val fragments: Array<Fragment> = arrayOf(
        DiscoveryFragment.newInstance(),
        RecommendFragment.newInstance(),
        DailyFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_home, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewpager.currentItem = 1
        iv_calendar.visibility = View.VISIBLE
    }

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        if (event is RefreshEvent && event.clazz == this::class.java) {
            when (viewpager.currentItem) {
                HOME_DISCOVERY -> EventBus.getDefault().post(DiscoveryFragment::class.java)
                HOME_RECOMMEND -> EventBus.getDefault().post(RecommendFragment::class.java)
                HOME_DAILY -> EventBus.getDefault().post(DailyFragment::class.java)
            }
        } else if (event is SwitchPageEvent) {
            when (event.clazz) {
                DiscoveryFragment::class.java -> viewpager.currentItem = 0
                RecommendFragment::class.java -> viewpager.currentItem = 1
                DailyFragment::class.java -> viewpager.currentItem = 2
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        const val HOME_DISCOVERY = 0
        const val HOME_RECOMMEND = 1
        const val HOME_DAILY = 2
    }
}