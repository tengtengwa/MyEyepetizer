package com.example.main.notification.push

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.base.BaseFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver
import kotlinx.android.synthetic.main.main_fragment_push.*

class PushFragment : BaseFragment() {

    private val pushViewModel by viewModels<PushViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

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
    }

    override fun observe() {
        pushViewModel.switchedDataList.observe(viewLifecycleOwner) {
        }
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                this::class.java -> {
                    if (rl_push_list.adapter?.itemCount ?: 0 > 0) {
                        rl_push_list.scrollToPosition(0)
                    }
                    sRefreshLayout.autoRefresh()
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = PushFragment()
    }
}