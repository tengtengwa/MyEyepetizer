package com.example.main.notification.push

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseFragment
import com.example.base.utils.GlobalUtil
import com.example.base.utils.logD
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.logic.model.PushMessage
import com.example.main.utils.EventObserver
import com.example.main.utils.InjectUtil
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.main_fragment_push.*

class PushFragment : BaseFragment() {

    //这个Fragment的扩展函数viewModels其实还是调用了ViewModelProvider的get方法来创建相应的ViewModel，但是它做了缓存
    private val pushViewModel by viewModels<PushViewModel>({ this }, { InjectUtil.getPushViewModelFactory() })

    //这个activityViewModels方法类似
    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var pushAdapter: PushAsyncAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_push, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pushAdapter = PushAsyncAdapter()
        rl_push_list.apply {
            adapter = pushAdapter
            layoutManager = LinearLayoutManager(hostActivity)
            //itemview数据的改变不会影响RecyclerView的宽高时，置为true可以避免RecyclerView重新layout
            //也就是说子item时通过这个方法对RecyclerView优化
            setHasFixedSize(true)
        }
        srl_push.apply {
            setOnRefreshListener {
                pushViewModel.requestDataList()
                logD(TAG, "refreshing")
            }
            setOnLoadMoreListener {
                pushViewModel.requestNextPageData()
                logD(TAG, "loading")
            }
        }
    }

    override fun loadData() {
        pushViewModel.requestDataList()
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.main_load_error_unknown)) {
            loadingView?.visibility = View.VISIBLE
            loadData()
        }
    }

    override fun observe() {
        pushViewModel.switchedDataList.observe(viewLifecycleOwner, Observer {
            val response = it.getOrNull()
            if (response == null) {
                srl_push.closeHeaderOrFooter()
                loadFailed(GlobalUtil.getString(R.string.base_error_view_tip))
                return@Observer
            }
            pushViewModel.nextPageUrl = response.nextPageUrl
            loadFinished()
            //首次进入页面时
            if (response.itemList.isNullOrEmpty() && pushViewModel.dataList.isNullOrEmpty()) {
                srl_push.closeHeaderOrFooter()
                return@Observer
            }
            //上拉加载时没有更多数据
            if (response.itemList.isNullOrEmpty() && pushViewModel.dataList.isNotEmpty()) {
                srl_push.finishLoadMoreWithNoMoreData()
                return@Observer
            }
            when (srl_push.state) {
                RefreshState.None, RefreshState.Refreshing -> {
                    pushViewModel.dataList.clear()
                    pushAdapter.submitList(response.itemList)
                }
                RefreshState.Loading -> {
                    val newList = ArrayList<PushMessage.Message>().apply {
                        addAll(pushViewModel.dataList)
                        addAll(response.itemList)
                    }
                    pushAdapter.submitList(newList)
                }
                else -> {}
            }
            pushViewModel.dataList.addAll(response.itemList)
            logD(TAG, "fetch data size : ${response.itemList.size} cur size：${pushViewModel.dataList.size}")
            if (pushViewModel.nextPageUrl.isNullOrEmpty()) {
                srl_push.finishLoadMoreWithNoMoreData()
            } else {
                srl_push.closeHeaderOrFooter()
            }
        })
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            if (it == this::class.java) {
                if (rl_push_list.adapter?.itemCount ?: 0 > 0) { //Adapter没有初始化的时候不会执行下面代码
                    rl_push_list.scrollToPosition(0)
                }
                srl_push.autoRefresh()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }
}