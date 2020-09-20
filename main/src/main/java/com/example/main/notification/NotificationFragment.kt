package com.example.main.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.base.utils.GlobalUtil
import com.example.base.utils.logD
import com.example.main.BaseViewPagerFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.notification.inbox.InboxFragment
import com.example.main.notification.interaction.InteractionFragment
import com.example.main.notification.push.PushFragment
import com.example.main.utils.EventObserver
import kotlinx.android.synthetic.main.main_fragment_notification.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*

class NotificationFragment : BaseViewPagerFragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override val fragments: Array<Fragment> = arrayOf(
        PushFragment.newInstance(),
        InteractionFragment.newInstance(),
        InboxFragment.newInstance()
    )

    override val tabTitles: Array<String> = arrayOf(
        GlobalUtil.getString(R.string.main_notification_tab1),
        GlobalUtil.getString(R.string.main_notification_tab2),
        GlobalUtil.getString(R.string.main_notification_tab3)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_notification, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_calendar.visibility = View.INVISIBLE
        viewpager.currentItem = 0
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            logD("refreshPageEvent", "class: notification.class")
            if (it == NotificationFragment::class.java) {
                when (viewpager.currentItem) {
                    NOTIFICATION_PUSH -> mainViewModel.refreshPage(PushFragment::class.java)
                    NOTIFICATION_INBOX -> mainViewModel.refreshPage(InboxFragment::class.java)
                    NOTIFICATION_INTERACTION -> mainViewModel.refreshPage(InteractionFragment::class.java)
                }
            }
        })
        mainViewModel.switchPageEvent.observe(this, EventObserver {
            when (it) {
                PushFragment::class.java -> viewpager.currentItem = 0
                InteractionFragment::class.java -> viewpager.currentItem = 1
                InboxFragment::class.java -> viewpager.currentItem = 2
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
        const val NOTIFICATION_PUSH = 0
        const val NOTIFICATION_INBOX = 1
        const val NOTIFICATION_INTERACTION = 2
    }
}