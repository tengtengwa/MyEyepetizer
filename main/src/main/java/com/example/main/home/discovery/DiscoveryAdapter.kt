package com.example.main.home.discovery

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.MainItemSpecialSquareCardCollectionBinding
import com.example.main.logic.model.Discovery

class DiscoveryAdapter : ListAdapter<Discovery, RecyclerView.ViewHolder>(DiscoveryDiffCallback()) {

    class DiscoveryDiffCallback : DiffUtil.ItemCallback<Discovery>() {
        override fun areItemsTheSame(oldItem: Discovery, newItem: Discovery): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Discovery, newItem: Discovery): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class SpecialSquareCardCollectionAdapter(val dataList: List<Discovery.ItemX>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class ViewHolder(private val binding: MainItemSpecialSquareCardCollectionBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Discovery.DataX) {
                binding.apply {
                    squareCardData = item
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }
    }
}