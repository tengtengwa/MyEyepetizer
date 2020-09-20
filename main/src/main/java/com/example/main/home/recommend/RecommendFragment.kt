package com.example.main.home.recommend

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

class RecommendFragment : BaseFragment() {

    private val pushViewModel by viewModels<RecommendViewModel>()

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_fragment_home_recommend, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            if (it == this::class.java) {
                //TODO("在这里刷新数据")
            }
        })
    }

    companion object {
        fun newInstance() = RecommendFragment()
    }

}