package com.example.main.notification.push

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.base.BaseFragment
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.main.R
import kotlinx.android.synthetic.main.main_fragment_push.*

class PushFragment : BaseFragment() {

    private val pushViewModel by activityViewModels<PushViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_push, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rl_push_list.apply {

        }
        observe()
    }

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        if (event is RefreshEvent && event.clazz == javaClass) {
            if (rl_push_list.adapter?.itemCount ?: 0 > 0) {
                rl_push_list.scrollToPosition(0)
            }
            sRefreshLayout.autoRefresh()
        }
    }

    private fun observe() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }
}