package com.example.main.home.discovery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.base.utils.toast
import com.example.main.common.RecyclerViewHelper
import com.example.main.common.SpecialSquareCardCollectionViewHolder
import com.example.main.databinding.MainItemSpecialSquareCardCollectionBinding
import com.example.main.logic.model.Discovery

class DiscoveryAdapter(private val dataList: List<Discovery.Item>) :
    ListAdapter<Discovery.Item, RecyclerView.ViewHolder>(DiscoveryDiffCallback()) {

    override fun getItemViewType(position: Int): Int = RecyclerViewHelper.getItemViewType(dataList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = dataList[position]
        when (holder) {
            is SpecialSquareCardCollectionViewHolder -> {
                holder.bind(itemData)
            }
        }
        //TODO("暂未完全完成")
    }

    override fun getItemCount() = dataList.size


    /**
     * 热门分类下16个子项的Adapter
     */
    class SpecialSquareCardCollectionAdapter : ListAdapter<Discovery.ItemX, SpecialSquareCardCollectionAdapter.ViewHolder>(TopCategoriesItemDiffCallback()) {

        inner class ViewHolder(private val binding: MainItemSpecialSquareCardCollectionBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Discovery.DataX) {
                binding.apply {
                    squareCardData = item
                    executePendingBindings()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val holder = ViewHolder(
                MainItemSpecialSquareCardCollectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            holder.itemView.setOnClickListener {
                "该功能暂未开放，尽请期待".toast()
                //todo("在这里为热门分类的子项设置点击事件，跳转到具体的分类页面")
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemData = currentList[position]
            holder.bind(itemData.data)
        }

        override fun getItemCount() = currentList.size
    }
}