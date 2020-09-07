package com.example.main.community.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver


class FollowFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return onCreateView(inflater.inflate(R.layout.main_fragment_follow, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()

    }

    override fun handleMessageEvent(event: MessageEvent) {
        super.handleMessageEvent(event)
        if (event is RefreshEvent && event.clazz == this::class.java) {
            TODO("在这里刷新数据")
        }
    }

    private fun observe() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = FollowFragment()
    }
}