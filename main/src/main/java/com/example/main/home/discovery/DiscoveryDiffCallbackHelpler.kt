package com.example.main.home.discovery

import androidx.recyclerview.widget.DiffUtil
import com.example.main.logic.model.Discovery

class DiscoveryDiffCallback : DiffUtil.ItemCallback<Discovery.Item>() {

    override fun areItemsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item): Boolean {
        TODO("根据不同的itemview来进行不同的判断逻辑")
    }

    override fun areContentsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item): Boolean {
        TODO("根据不同的itemview来进行不同的判断逻辑")
    }
}

class TopCategoriesItemDiffCallback : DiffUtil.ItemCallback<Discovery.ItemX>() {
    override fun areItemsTheSame(oldItem: Discovery.ItemX, newItem: Discovery.ItemX) =
        oldItem.data.title == newItem.data.title

    override fun areContentsTheSame(oldItem: Discovery.ItemX, newItem: Discovery.ItemX) =
        oldItem.data.image == newItem.data.image
}