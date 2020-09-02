package com.example.main.home.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.main.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver

/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyFragment : BaseFragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment_daily, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observe()
    }

    private fun observe() {
        mainViewModel.isRefreshHomeDaily.observe(viewLifecycleOwner, EventObserver {
            TODO("在这里刷新数据")
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DailyFragment()
    }
}