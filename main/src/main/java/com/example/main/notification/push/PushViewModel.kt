package com.example.main.notification.push

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.main.logic.MainRepository
import com.example.main.logic.model.PushMessage

class PushViewModel(val repository: MainRepository) : ViewModel() {

    var dataList = ArrayList<PushMessage.Message>()

    var nextPageUrl: String? = null

    private var dataListParams = MutableLiveData<String>()

/*    val switchedDataList = Transformations.switchMap(dataListParams) { url ->
        liveData {

        }
    }*/
}