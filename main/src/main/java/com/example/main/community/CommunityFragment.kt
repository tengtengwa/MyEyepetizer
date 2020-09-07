package com.example.main.community

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
import com.example.main.community.follow.FollowFragment
import com.example.main.home.recommend.RecommendFragment
import kotlinx.android.synthetic.main.main_fragment_community.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*
import org.greenrobot.eventbus.EventBus

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

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        if (event is RefreshEvent && event.clazz == this::class.java) {
            when (viewpager.currentItem) {
                COMMUNITY_RECOMMEND -> EventBus.getDefault()
                    .post(com.example.main.community.recommend.RecommendFragment::class.java)
                COMMUNITY_FOLLOW -> EventBus.getDefault().post(FollowFragment::class.java)
            }
        }else if (event is SwitchPageEvent) {
            when (event.clazz) {
                com.example.main.community.recommend.RecommendFragment::class.java -> viewpager.currentItem = 0
                FollowFragment::class.java -> viewpager.currentItem = 1
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CommunityFragment()

        const val COMMUNITY_RECOMMEND = 0
        const val COMMUNITY_FOLLOW = 1
    }
}