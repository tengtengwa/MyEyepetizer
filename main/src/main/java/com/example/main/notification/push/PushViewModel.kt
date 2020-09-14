package com.example.main.notification.push

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.main.logic.MainRepository
import com.example.main.logic.model.PushMessage
import com.example.main.logic.network.service.MainPageService
import java.lang.Exception

class PushViewModel(private val repository: MainRepository) : ViewModel() {

    var dataList = ArrayList<PushMessage.Message>()

    var nextPageUrl: String? = null

    private var dataListParams = MutableLiveData<String>()

    val switchedDataList = Transformations.switchMap(dataListParams) { url ->
        liveData {
            val response = try {
                val pushResponse = repository.refreshNotificationPush(url)
                Result.success(pushResponse)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(response)
        }
    }

    fun requestDataList() {
        dataListParams.value = MainPageService.PUSHMESSAGE_URL
    }

    fun requestNextPageData() {
        dataListParams.value = nextPageUrl ?: ""
    }
}