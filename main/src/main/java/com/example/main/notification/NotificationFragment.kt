package com.example.main.notification

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
import com.example.main.notification.inbox.InboxFragment
import com.example.main.notification.interaction.InteractionFragment
import com.example.main.notification.push.PushFragment
import kotlinx.android.synthetic.main.main_fragment_notification.*
import kotlinx.android.synthetic.main.main_layout_tabbar.*
import org.greenrobot.eventbus.EventBus

class NotificationFragment : BaseViewPagerFragment() {

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
    }

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        //下面其实可以偷点懒，因为砍掉了登陆功能，只有PushFragment可以刷新
        if (event is RefreshEvent && event.clazz == this::class.java) {
            when (viewpager.currentItem) {
                NOTIFICATION_PUSH -> EventBus.getDefault().post(PushFragment::class.java)
                NOTIFICATION_INBOX -> EventBus.getDefault().post(InboxFragment::class.java)
                NOTIFICATION_INTERACTION -> EventBus.getDefault().post(InteractionFragment::class.java)
            }
        } else if (event is SwitchPageEvent) {
            when (event.clazz) {
                PushFragment::class.java -> viewpager.currentItem = 0
                InteractionFragment::class.java -> viewpager.currentItem = 1
                InboxFragment::class.java -> viewpager.currentItem = 2
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationFragment()
        const val NOTIFICATION_PUSH = 0
        const val NOTIFICATION_INBOX = 1
        const val NOTIFICATION_INTERACTION = 2
    }
}