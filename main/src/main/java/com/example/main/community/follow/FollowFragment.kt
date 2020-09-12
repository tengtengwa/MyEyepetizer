package com.example.main.community.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.base.BaseFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver


class FollowFragment : BaseFragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

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

    override fun loadData() {
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            if (it == this::class.java) {
                TODO("在这里刷新数据")
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = FollowFragment()
    }
}