package com.example.main.notification.push

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.MainItemPushBinding
import com.example.main.logic.model.PushMessage

class PushAdapter(val fragment: PushFragment, private val dataList: List<PushMessage.Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PushViewHolder(
        MainItemPushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = dataList[position]
        (holder as PushViewHolder).bind(dataItem)
    }

    override fun getItemCount() = dataList.size

    class PushViewHolder(private val binding: MainItemPushBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                TODO("在这里处理子项点击事件")
            }
        }

        fun bind(dataItem: PushMessage.Message) {
            binding.apply {
                messageItem = dataItem
                executePendingBindings()
            }
        }
    }
}