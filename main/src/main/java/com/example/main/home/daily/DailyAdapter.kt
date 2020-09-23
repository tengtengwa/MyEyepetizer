package com.example.main.home.daily

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.home.discovery.DiscoveryFragment
import com.example.main.logic.model.Daily
import com.example.main.logic.model.Discovery

class DailyAdapter(
    private val fragment: DiscoveryFragment,
    private val dataList: List<Daily.Item>
) : ListAdapter<Daily.Item, RecyclerView.ViewHolder>(DailyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        const val DAILY_LIBRARY_TYPE = "DAILY"
    }
}