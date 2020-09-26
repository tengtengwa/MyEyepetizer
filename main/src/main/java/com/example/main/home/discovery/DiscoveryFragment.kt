package com.example.main.home.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.base.BaseFragment
import com.example.base.utils.GlobalUtil
import com.example.base.utils.logD
import com.example.main.R
import com.example.main.common.MainViewModel
import com.example.main.utils.EventObserver
import com.example.main.utils.InjectUtil
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.main_fragment_discovery.*

class DiscoveryFragment : BaseFragment() {

    private val rotateAnimation = RotateAnimation(0f, 360f,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
        interpolator = LinearInterpolator()
        duration = 1000L
        repeatCount = Animation.INFINITE
        repeatMode = Animation.RESTART
    }

    private val discoveryViewModel by viewModels<DiscoveryViewModel>({ this }, { InjectUtil.getDiscoveryViewModelFactory() })

    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var discoveryAdapter: DiscoveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_discovery, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discoveryAdapter = DiscoveryAdapter(this, discoveryViewModel.dataList)
        srl_discovery.apply {
            setOnRefreshListener {
                discoveryViewModel.requestDataList()
            }
            setOnLoadMoreListener {
                discoveryViewModel.requestNextPageData()
            }
        }
        rv_discovery_list.apply {
            adapter = discoveryAdapter
        }
    }

    override fun loadData() {
        discoveryViewModel.requestDataList()
        loadingView?.startAnimation(rotateAnimation)
        startLoading()
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        rv_discovery_list.visibility = View.INVISIBLE
        loadingView?.clearAnimation()
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.main_load_error_unknown)) {
            loadingView?.visibility = View.VISIBLE
            loadData()
        }
    }

    override fun loadFinished() {
        super.loadFinished()
        rv_discovery_list.visibility = View.VISIBLE
        loadingView?.clearAnimation()
    }

    override fun observe() {
        discoveryViewModel.switchedDiscoveryList.observe(viewLifecycleOwner, Observer {
            val response = it.getOrNull()
            if (response == null) {
                srl_discovery.closeHeaderOrFooter()
                loadFailed(GlobalUtil.getString(R.string.base_error_view_tip))
                return@Observer
            }
            discoveryViewModel.nextPageUrl = response.nextPageUrl
            loadFinished()
            //首次进入页面时
            if (response.itemList.isNullOrEmpty() && discoveryViewModel.dataList.isNullOrEmpty()) {
                srl_discovery.closeHeaderOrFooter()
                return@Observer
            }
            //上拉加载时没有更多数据
            if (response.itemList.isNullOrEmpty() && discoveryViewModel.dataList.isNotEmpty()) {
                srl_discovery.finishLoadMoreWithNoMoreData()
                return@Observer
            }
            when (srl_discovery.state) {
                RefreshState.None, RefreshState.Refreshing -> {
                    discoveryViewModel.dataList.clear()
                    discoveryAdapter.submitList(response.itemList)
                }
                else -> {}
            }
            discoveryViewModel.dataList.addAll(response.itemList)
            logD(TAG, "fetch data size : ${response.itemList.size} cur size：${discoveryViewModel.dataList.size}")
            if (discoveryViewModel.nextPageUrl.isNullOrEmpty()) {
                srl_discovery.finishLoadMoreWithNoMoreData()
            } else {
                srl_discovery.closeHeaderOrFooter()
            }
        })
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            if (it == this::class.java) {
                if (rv_discovery_list.adapter?.itemCount ?: 0 > 0) { //Adapter没有初始化的时候不会执行下面代码
                    rv_discovery_list.scrollToPosition(0)
                }
                srl_discovery.autoRefresh()
            }
        })
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}
