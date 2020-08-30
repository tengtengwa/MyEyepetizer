package com.example.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.main.BaseViewPagerFragment
import com.example.main.R
import com.example.main.home.daily.DailyFragment
import com.example.main.home.discovery.DiscoveryFragment
import com.example.main.home.recommend.RecommendFragment

class HomeFragment : BaseViewPagerFragment() {

    override val tabTitles: Array<String> = arrayOf(
            resources.getString(R.string.main_tab_text_1),
            resources.getString(R.string.main_tab_text_2),
            resources.getString(R.string.main_tab_text_3)
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
        return inflater.inflate(R.layout.main_fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}