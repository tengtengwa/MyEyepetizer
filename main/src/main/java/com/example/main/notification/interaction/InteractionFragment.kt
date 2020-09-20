package com.example.main.notification.interaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.base.BaseFragment
import com.example.base.StartService
import com.example.main.common.MainViewModel
import com.example.main.R
import com.example.main.utils.EventObserver
import kotlinx.android.synthetic.main.main_fragment_interaction.*

/**
 * 首页-通知中的互动页面
 */
class InteractionFragment : BaseFragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return onCreateView(inflater.inflate(R.layout.main_fragment_interaction, container, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_login.setOnClickListener {
            StartService.startLogin()
        }
    }

    override fun observe() {
        mainViewModel.refreshPageEvent.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                this::class.java -> {
                    //todo("在这里刷新数据")
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = InteractionFragment()
    }
}