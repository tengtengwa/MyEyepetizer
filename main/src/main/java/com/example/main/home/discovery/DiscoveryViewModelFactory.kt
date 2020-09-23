package com.example.main.home.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.main.logic.MainRepository
import com.example.main.logic.dao.MainPageDao
import com.example.main.logic.network.MainPageNetwork

class DiscoveryViewModelFactory(private val repository: MainRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DiscoveryViewModel(repository) as T
}