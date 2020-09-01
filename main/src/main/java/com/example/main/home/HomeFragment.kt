package com.example.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.main.BaseViewPagerFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.home.daily.DailyFragment
import com.example.main.home.discovery.DiscoveryFragment
import com.example.main.home.recommend.RecommendFragment
import kotlinx.android.synthetic.main.main_fragment_home.*
import kotlinx.android.synthetic.main.main_layout_home_titlebar.*

class HomeFragment : BaseViewPagerFragment() {

    override val tabTitles: Array<String> = arrayOf(
        resources.getString(R.string.main_tab_text_1),
        resources.getString(R.string.main_tab_text_2),
        resources.getString(R.string.main_tab_text_3)
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
        iv_calender.visibility = View.VISIBLE
        mainViewModel = ViewModelProviders.of(hostActivity).get(MainViewModel::class.java)
        mainViewModel.isRefreshHomePage.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                mainViewModel.apply {
                    when (viewpager.currentItem) {
                        0 -> homePageRefresh.value = MainViewModel.REFRESH_HOME_DISCOVERY
                        1 -> homePageRefresh.value = MainViewModel.REFRESH_HOME_RECOMMEND
                        2 -> homePageRefresh.value = MainViewModel.REFRESH_HOME_DAILY
                    }
                    //本次事件通知完成后将这两个值重置，以便接收下一次通知
                    homePageRefresh.value = -1
                    isRefreshHomePage.value = false
                    TODO("这里将两个值重置可能会出现bug，完了调试的时候再看")
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}