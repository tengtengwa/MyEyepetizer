package com.example.main.notification.push

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.base.utils.logD
import com.example.main.databinding.MainItemPushBinding
import com.example.main.logic.model.PushMessage
import com.example.main.utils.ActionUrlUtil

class PushAdapter(val fragment: PushFragment, private var dataList: ArrayList<PushMessage.Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PushViewHolder {
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
            val item = dataList[holder.bindingAdapterPosition]
            it.setOnClickListener {
                ActionUrlUtil.process(item.actionUrl)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = dataList[position]
        logD("dataitem", dataItem.title)
        (holder as PushViewHolder).bind(dataItem)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        onBindViewHolder(holder, position)
    }

    override fun getItemCount() = dataList.size

    fun setData(newList: ArrayList<PushMessage.Message>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(dataList, newList), false)
        diffResult.dispatchUpdatesTo(this)
        dataList.apply {
            clear()
            addAll(newList)
        }
    }

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