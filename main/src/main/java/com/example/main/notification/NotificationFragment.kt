package com.example.main.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var mainViewModel: MainViewModel

    override val fragments: Array<Fragment> = arrayOf(
        PushFragment.newInstance(),
        InteractionFragment.newInstance(),
        InboxFragment.newInstance()
    )

    override val tabTitles: Array<String> = arrayOf(
        resources.getString(R.string.main_notification_tab1),
        resources.getString(R.string.main_notification_tab2),
        resources.getString(R.string.main_notification_tab3)
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
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.isRefreshNotificationPage.observe(viewLifecycleOwner, EventObserver {
            mainViewModel.apply {
                when (viewpager.currentItem) {
                    0 -> isRefreshNotificationPush
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
    }
}