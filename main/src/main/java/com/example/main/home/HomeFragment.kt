package com.example.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var mainViewModel : MainViewModel

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
        mainViewModel = ViewModelProvider(hostActivity)[MainViewModel::class.java]
        mainViewModel.isRefreshHomePage.observe(viewLifecycleOwner, EventObserver {
            mainViewModel.apply {
                when (viewpager.currentItem) {
                    0 -> refreshHomeDiscovery()
                    1 -> refreshHomeRecommend()
                    2 -> refreshHomeDaily()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}