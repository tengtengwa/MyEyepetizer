package com.example.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base.BaseFragment
import com.example.base.StartService
import com.example.base.utils.setOnClickListener
import com.example.base.utils.toast
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.main_fragment_home.*
import kotlinx.android.synthetic.main.main_layout_home_titlebar.*

/**
 * 页面中含有ViewPager和TabLayout的所有Fragment的基类，其中封装了tablayout和ViewPager绑定的逻辑
 */
abstract class BaseViewPagerFragment : BaseFragment() {

    protected abstract val fragments : Array<Fragment>

    protected abstract val tabTitles: Array<String>

    private val viewPagerAdapter by lazy { ViewPagerAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    open fun setupViews() {
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
        viewpager.adapter = viewPagerAdapter

        setOnClickListener(iv_calender, iv_search) {
            when (this) {
                iv_calender -> "此功能即将开放，尽请期待吧~".toast(hostActivity)
                iv_search -> StartService.startLogin()
            }
        }
    }

    inner class ViewPagerAdapter : FragmentStateAdapter(hostActivity) {
        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]
    }
}