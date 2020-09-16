package com.example.main.utils

import com.example.main.logic.MainRepository
import com.example.main.logic.dao.MainPageDao
import com.example.main.logic.network.MainPageNetwork
import com.example.main.notification.push.PushViewModelFactory

object InjectUtil {

    private fun getMainRepository() = MainRepository.getInstance(MainPageNetwork.getInstance(), MainPageDao)

    fun getPushViewModelFactory() = PushViewModelFactory(getMainRepository())

}