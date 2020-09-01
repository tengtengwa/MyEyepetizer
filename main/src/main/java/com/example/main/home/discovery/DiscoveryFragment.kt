package com.example.main.home.discovery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.base.BaseFragment
import com.example.main.MainViewModel
import com.example.main.R

class DiscoveryFragment : BaseFragment() {

    private lateinit var viewModel: DiscoveryViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_discovery_fragment, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DiscoveryViewModel::class.java)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observe()
    }

    private fun observe() {
        mainViewModel.homePageRefresh.observe(viewLifecycleOwner, Observer {
            if (it == MainViewModel.REFRESH_HOME_DISCOVERY) {
                TODO("在这里刷新数据")
            }
        })
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}
