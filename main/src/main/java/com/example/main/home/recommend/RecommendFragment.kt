package com.example.main.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.main.MainViewModel
import com.example.main.R

class RecommendFragment : BaseFragment() {

    private lateinit var viewModel: RecommendViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater.inflate(R.layout.main_recommend_fragment, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RecommendViewModel::class.java]
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observe()
    }

    private fun observe() {
        mainViewModel.isRefreshHomeRecommend.observe(viewLifecycleOwner, Observer {
            it.getEventIfNotHandled()?.let {
                TODO("在这里刷新数据")
            }
        })
    }

    companion object {
        fun newInstance() = RecommendFragment()
    }

}