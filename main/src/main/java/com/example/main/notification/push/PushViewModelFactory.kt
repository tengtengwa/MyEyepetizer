package com.example.main.notification.push

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.main.logic.MainRepository

class PushViewModelFactory(private val repository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PushViewModel(repository) as T
}