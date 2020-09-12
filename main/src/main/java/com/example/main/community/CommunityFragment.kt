package com.example.main.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    override val tabTitles: Array<String> = arrayOf(
        resources.getString(R.string.main_community_tab1),
        resources.getString(R.string.main_community_tab2)
    )

    override val fragments: Array<Fragment> = arrayOf(
        FollowFragment.newInstance(),
        RecommendFragment.newInstance()
    )

    private val mainViewModel by activityViewModels<MainViewModel>()

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
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(this, EventObserver {
            if (it == this::class.java) {
                when (viewpager.currentItem) {
                    COMMUNITY_RECOMMEND -> mainViewModel.refreshPage(com.example.main.community.recommend.RecommendFragment::class.java)
                    COMMUNITY_FOLLOW -> mainViewModel.refreshPage(FollowFragment::class.java)
                }
            }
        })
        mainViewModel.switchPagerEvent.observe(this, EventObserver {
            when (it) {
                com.example.main.community.recommend.RecommendFragment::class.java -> viewpager.currentItem = 0
                FollowFragment::class.java -> viewpager.currentItem = 1
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CommunityFragment()

        const val COMMUNITY_RECOMMEND = 0
        const val COMMUNITY_FOLLOW = 1
    }
}