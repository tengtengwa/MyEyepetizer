package com.example.main.home.discovery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.main.logic.MainRepository
import com.example.main.logic.model.Discovery
import com.example.main.logic.network.service.MainPageService
import java.lang.Exception

class DiscoveryViewModel(private val repository: MainRepository) : ViewModel() {

    var dataList = ArrayList<Discovery.Item>()

    val discoveryList = MutableLiveData<Discovery>()

    var nextPage: String? = null

    private var discoveryParam = MutableLiveData<String>()

    val switchedDiscoveryList = Transformations.switchMap(discoveryParam) { url ->
        liveData {
            val result = try {
                val response = repository.refreshHomeDiscovery(url)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure<Discovery>(e)
            }
            emit(result)
        }
    }

    fun requestDataList() {
        discoveryParam.value = MainPageService.DISCOVERY_URL
    }

    fun requestNextPageData() {
        discoveryParam.value = nextPage ?: ""
    }
}