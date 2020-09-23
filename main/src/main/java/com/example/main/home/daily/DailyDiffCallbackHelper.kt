package com.example.main.home.daily

import androidx.recyclerview.widget.DiffUtil
import com.example.main.logic.model.Daily

class DailyDiffCallback : DiffUtil.ItemCallback<Daily.Item>() {

    override fun areItemsTheSame(oldItem: Daily.Item, newItem: Daily.Item): Boolean {
        TODO("根据不同的itemview来进行不同的判断逻辑")
    }

    override fun areContentsTheSame(oldItem: Daily.Item, newItem: Daily.Item): Boolean {
        TODO("根据不同的itemview来进行不同的判断逻辑")
    }
}