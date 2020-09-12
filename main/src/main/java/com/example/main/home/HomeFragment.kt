package com.example.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.main.BaseViewPagerFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.home.daily.DailyFragment
import com.example.main.home.discovery.DiscoveryFragment
import com.example.main.home.recommend.RecommendFragment
import com.example.main.utils.EventObserver
import kotlinx.android.synthetic.main.main_fragment_home.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*

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

    private val mainViewModel by activityViewModels<MainViewModel>()

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

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(this, EventObserver {
            if (it == this::class.java) {
                when (viewpager.currentItem) {
                    HOME_DISCOVERY -> mainViewModel.refreshPage(DiscoveryFragment::class.java)
                    HOME_RECOMMEND -> mainViewModel.refreshPage(RecommendFragment::class.java)
                    HOME_DAILY -> mainViewModel.refreshPage(DailyFragment::class.java)
                }
            }
        })
        mainViewModel.switchPagerEvent.observe(this, EventObserver {
            when (it) {
                DiscoveryFragment::class.java -> viewpager.currentItem = 0
                RecommendFragment::class.java -> viewpager.currentItem = 1
                DailyFragment::class.java -> viewpager.currentItem = 2
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

        /**
         * 所有带有ViewPager的Fragment都通过这些常量替代tab的下标来表示相应的Fragment
         */
        const val HOME_DISCOVERY = 0
        const val HOME_RECOMMEND = 1
        const val HOME_DAILY = 2
    }
}