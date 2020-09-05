package com.example.main.notification.interaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.base.StartService
import com.example.main.R
import kotlinx.android.synthetic.main.main_fragment_interaction.*

/**
 * 首页-通知中的互动页面
 */
class InteractionFragment : BaseFragment() {

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

    companion object {
        @JvmStatic
        fun newInstance() = InteractionFragment()
    }
}