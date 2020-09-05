package com.example.main.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.main.BaseViewPagerFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.community.follow.FollowFragment
import com.example.main.home.recommend.RecommendFragment
import com.example.main.utils.EventObserver
import kotlinx.android.synthetic.main.main_fragment_community.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*

/**
 * 主页面中含有ViewPager的CommunityFragment
 */
class CommunityFragment : BaseViewPagerFragment() {

    private lateinit var mainViewModel: MainViewModel

    override val tabTitles: Array<String> = arrayOf(
        resources.getString(R.string.main_community_tab1),
        resources.getString(R.string.main_community_tab2)
    )

    override val fragments: Array<Fragment> = arrayOf(
        FollowFragment.newInstance(),
        RecommendFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return onCreateView(inflater.inflate(R.layout.main_fragment_community, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_calendar.visibility = View.INVISIBLE
        viewpager.currentItem = 0
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.isRefreshCommunityPage.observe(viewLifecycleOwner, EventObserver {
            mainViewModel.apply {
                when (viewpager.currentItem) {
                    0 -> refreshCommunityRecommend()
                    1 -> refresCommunityFollow()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CommunityFragment()
    }
}