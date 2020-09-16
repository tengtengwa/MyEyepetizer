package com.example.main.notification.push

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.MainItemPushBinding
import com.example.main.logic.model.PushMessage
import com.example.main.utils.ActionUrlUtil

class PushAsyncAdapter : ListAdapter<PushMessage.Message, RecyclerView.ViewHolder>(PushMessageDiffCallback()) {

    private class PushMessageDiffCallback : DiffUtil.ItemCallback<PushMessage.Message>() {
        override fun areItemsTheSame(
            oldItem: PushMessage.Message,
            newItem: PushMessage.Message
        ): Boolean = oldItem.title == newItem.title && oldItem.date == newItem.date

        override fun areContentsTheSame(
            oldItem: PushMessage.Message,
            newItem: PushMessage.Message
        ): Boolean = oldItem.content == newItem.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = PushViewHolder(
            MainItemPushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.itemView.setOnClickListener {
            /**
             * RecyclerView的1.2-alpha版本后新增了ConcatAdapter（alpha04版以前叫MergeAdapter），一个RecyclerView
             * 多个Adapter的情况调用getAdapterPosition会存在歧义，因此废弃了这个方法，又提供了getBindingAdapterPosition和
             * getAbsoluteAdapterPosition两个方法；前者获取当前Adapter中itemview的位置，后者获取这个itemview在
             * 整个RecyclerView的位置，详情参考郭霖这篇文章：
             * https://blog.csdn.net/guolin_blog/article/details/10560640yyy9
             *
             */
            val item = this.currentList[holder.bindingAdapterPosition]
            it.setOnClickListener {
                ActionUrlUtil.process(item.actionUrl)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = getItem(position)
        //logV("PushAsyncAdapter", dataItem.title)
        (holder as PushViewHolder).bind(dataItem)
    }

    override fun getItemCount() = this.currentList.size

    class PushViewHolder(private val binding: MainItemPushBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItem: PushMessage.Message) {
            binding.apply {
                messageItem = dataItem
                executePendingBindings()
            }
        }
    }
}