package com.example.main.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base.BaseFragment
import com.example.base.StartService
import com.example.base.utils.setOnClickListener
import com.example.base.utils.toast
import com.example.main.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_layout_tabbar.*

/**
 * 页面中含有ViewPager和TabLayout的所有Fragment的基类，其中封装了tablayout和ViewPager绑定的逻辑
 */
abstract class BaseViewPagerFragment : BaseFragment() {

    protected abstract val fragments : Array<Fragment>

    protected abstract val tabTitles: Array<String>

    //ViewPager2默认实现了懒加载，因此无需手动实现
    private var viewPager: ViewPager2? = null

    private val viewPagerAdapter by lazy { ViewPagerAdapter(requireActivity()).apply { addFragment(fragments) } }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    open fun setupViews() {
        initParams()
        setOnClickListener(iv_calendar, iv_search) {
            when (this) {
                iv_calendar -> "此功能即将开放，尽请期待吧~".toast(hostActivity)
                iv_search -> StartService.startLogin()
            }
        }
    }

    private fun initParams() {
        viewPager = rootView?.findViewById(R.id.viewpager)
        viewPager?.let {
            it.adapter = viewPagerAdapter
            //注意，下面TabLayoutMediator的attach方法必须在ViewPager设置Adapter之后调用
            TabLayoutMediator(tablayout, it) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()  //attach方法中会帮我们给tablayout设置TabSelectedListener、给ViewPager注册PageChangeCallback
        }
    }

    inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        private val fragmentList = mutableListOf<Fragment>()

        fun addFragment(fragments: Array<Fragment>) {
            fragmentList.addAll(fragments)
        }

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]
    }
}