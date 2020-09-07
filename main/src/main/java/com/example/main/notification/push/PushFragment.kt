package com.example.main.notification.push

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.main.R

class PushFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return onCreateView(inflater.inflate(R.layout.main_fragment_push, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        if (event is RefreshEvent && event.clazz == javaClass) {
            TODO("在这里刷新数据")
        }
    }

    private fun observe() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }
}