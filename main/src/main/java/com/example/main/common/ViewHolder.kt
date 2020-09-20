package com.example.main.common

import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.MainLayoutSpecialSquareCardCollectionBinding
import com.example.main.logic.model.Discovery

class SpecialSquareCardCollection(private val binding: MainLayoutSpecialSquareCardCollectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
    }

    fun bind(itemData: Discovery.Item) {
        binding.apply {
            item = itemData
            executePendingBindings()
        }
    }
}