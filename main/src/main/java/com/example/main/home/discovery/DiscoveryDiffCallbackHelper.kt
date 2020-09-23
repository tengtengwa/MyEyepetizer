package com.example.main.home.discovery

import androidx.recyclerview.widget.DiffUtil
import com.example.main.logic.model.Discovery

class DiscoveryDiffCallback : DiffUtil.ItemCallback<Discovery.Item>() {

    override fun areItemsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item) =
        when (oldItem.type) {
            "horizontalScrollCard", "specialSquareCardCollection", "columnCardList" -> {
                val bannerNum = oldItem.data.itemList.size
                var i = 0
                while (i < bannerNum) {
                    if (oldItem.data.itemList[i].data.title != newItem.data.itemList[i].data.title) {
                        break
                    }
                    i++
                }
                i == bannerNum
            }

            "banner", "videoSmallCard", "briefCard" -> oldItem.data.title == newItem.data.title

            else -> true
        }

    override fun areContentsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item) =
        when (oldItem.type) {
            "horizontalScrollCard", "columnCardList" -> {
                val bannerNum = oldItem.data.itemList.size
                var i = 0
                while (i < bannerNum) {
                    if (oldItem.data.itemList[i].data.image != newItem.data.itemList[i].data.image) {
                        break
                    }
                    i++
                }
                i == bannerNum
            }

            "videoSmallCard", "briefCard" -> oldItem.data.description == newItem.data.description

            "banner" -> oldItem.data.image == newItem.data.image

            else -> true
        }
}

/**
 * 首页-发现中热门分类、专题策划等布局中RecyclerView子项的比较器
 */
class SquareCardDiffCallback : DiffUtil.ItemCallback<Discovery.ItemX>() {
    override fun areItemsTheSame(oldItem: Discovery.ItemX, newItem: Discovery.ItemX) =
        oldItem.data.title == newItem.data.title

    override fun areContentsTheSame(oldItem: Discovery.ItemX, newItem: Discovery.ItemX) =
        oldItem.data.image == newItem.data.image
}
