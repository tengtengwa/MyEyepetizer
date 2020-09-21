package com.example.main.common

import androidx.recyclerview.widget.RecyclerView
import com.example.base.utils.setAllOnClickListener
import com.example.base.utils.toast
import com.example.main.databinding.MainLayoutSpecialSquareCardCollectionBinding
import com.example.main.home.discovery.DiscoveryAdapter
import com.example.main.logic.model.Discovery

/**
 * 首页-发现中的热门分类ViewHolder
 */
class SpecialSquareCardCollectionViewHolder(private val binding: MainLayoutSpecialSquareCardCollectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.gpTopCategories.setAllOnClickListener {
            //todo(热门分类跳转到全部分类页面)
            "该功能暂未开放，尽请期待".toast()
        }
    }

    fun bind(itemData: Discovery.Item) {
        binding.apply {
            item = itemData
            executePendingBindings()
            hrvTopCategoriesList.setHasFixedSize(true)
            hrvTopCategoriesList.isNestedScrollingEnabled = true
            val itemList = itemData.data.itemList       //squarecard的data列表
            binding.hrvTopCategoriesList.adapter =
                DiscoveryAdapter.SpecialSquareCardCollectionAdapter().apply {
                    submitList(itemList)
                }
        }
    }
}


object RecyclerViewHelper {

    fun getItemViewType(item: Discovery.Item) {
        getItemViewType(item.type, item.data.dataType)
    }

    private fun getItemViewType(type: String, dataType: String) = when (type) {
        "horizontalScrollCard" -> {

        }
        "specialSquareCardCollection" -> {

        }
        "columnCardList" -> {

        }
        "textCard" -> {

        }
        "banner" -> {

        }
        "videoSmallCard" -> {

        }
        "briefCard" -> {

        }
        else -> {

        }
    }
}