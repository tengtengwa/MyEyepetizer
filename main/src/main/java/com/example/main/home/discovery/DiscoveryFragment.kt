package com.example.main.home.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.base.BaseFragment
import com.example.base.event.MessageEvent
import com.example.base.event.RefreshEvent
import com.example.main.R

class DiscoveryFragment : BaseFragment() {

    private val viewModel by viewModels<DiscoveryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_discovery_fragment, container, false))
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
        fun newInstance() = DiscoveryFragment()
    }
}
