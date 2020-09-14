package com.example.main.notification.push

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseFragment
import com.example.base.utils.GlobalUtil
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver
import com.scwang.smart.refresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.main_fragment_push.*

class PushFragment : BaseFragment() {

    private val pushViewModel by viewModels<PushViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var pushAdapter: PushAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_push, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pushAdapter = PushAdapter(this@PushFragment, pushViewModel.dataList)
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
            }
            setOnLoadMoreListener {
                pushViewModel.requestNextPageData()
            }
        }
    }

    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.main_load_error_unknown)) {
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
            //下面数据变化通过DiffUtil来计算并进行局部刷新，防止在刷新时重新走一遍绘制流程，也就是解决闪一下屏的问题
            val oldDataList = pushViewModel.dataList
            if (srl_push.state == RefreshState.Refreshing) {
                pushViewModel.dataList.clear()
            }
            pushViewModel.dataList.addAll(response.itemList)    //ArrayList的addAdd方法返回的是boolean...
            val newDataList = pushViewModel.dataList
            val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(oldDataList, newDataList), false)
            pushAdapter.apply {
                dataList = newDataList      //必须先将新数据给Adapter,否则会造成ui刷新了但数据未更新的bug
                diffResult.dispatchUpdatesTo(this)
            }
            if (pushViewModel.nextPageUrl.isNullOrEmpty()) {
                srl_push.finishLoadMoreWithNoMoreData()
            } else {
                srl_push.closeHeaderOrFooter()
            }
        })
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                this::class.java -> {
                    if (rl_push_list.adapter?.itemCount ?: 0 > 0) { //Adapter没有初始化的时候不会执行下面代码
                        rl_push_list.scrollToPosition(0)
                    }
                    srl_push.autoRefresh()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }
}