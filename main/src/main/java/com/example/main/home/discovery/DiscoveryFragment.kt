package com.example.main.home.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.base.BaseFragment
import com.example.main.common.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver

class DiscoveryFragment : BaseFragment() {

    private val viewModel by viewModels<DiscoveryViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    private val adapter = DiscoveryAdapter(viewModel.dataList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_discovery, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            if (it == this::class.java) {
                TODO("在这里刷新数据")
            }
        })
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}
