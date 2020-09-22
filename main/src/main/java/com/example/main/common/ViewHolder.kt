package com.example.main.common

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.example.base.bean.Const
import com.example.base.bean.Const.ItemViewType.BANNER
import com.example.base.bean.Const.ItemViewType.COLUMN_CARD_LIST
import com.example.base.bean.Const.ItemViewType.HORIZONTAL_SCROLL_CARD
import com.example.base.bean.Const.ItemViewType.SPECIAL_SQUARE_CARD_COLLECTION
import com.example.base.bean.Const.ItemViewType.TAG_BRIEFCARD
import com.example.base.bean.Const.ItemViewType.TEXT_CARD_HEADER7
import com.example.base.bean.Const.ItemViewType.TEXT_CARD_HEADER8
import com.example.base.bean.Const.ItemViewType.UNKNOWN
import com.example.base.customview.CustomFontTextView
import com.example.base.customview.HorizontalRecyclerView
import com.example.base.utils.inflate
import com.example.main.R
import com.example.main.logic.model.Discovery

/**
 * 首页-发现中的热门分类ViewHolder
 */
class SpecialSquareCardCollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val rightText:CustomFontTextView = view.findViewById(R.id.tv_right)

    val group: Group = view.findViewById(R.id.gp_top_categories)

    val recyclerView: HorizontalRecyclerView = view.findViewById(R.id.hrv_top_categories_list)
}

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

object RecyclerViewHelper {

    fun getItemViewType(item: Discovery.Item) = getItemViewType(item.type, item.data.dataType)


    private fun getItemViewType(type: String, dataType: String): Int = when (type) {
        "horizontalScrollCard" -> {
            when (dataType) {
                "HorizontalScrollCard" -> HORIZONTAL_SCROLL_CARD
                else -> UNKNOWN
            }
        }
        "specialSquareCardCollection" -> {
            when (dataType) {
                "ItemCollection" -> SPECIAL_SQUARE_CARD_COLLECTION
                else -> UNKNOWN
            }
        }
        "columnCardList" -> {
            when (dataType) {
                "ItemCollection" -> COLUMN_CARD_LIST
                else -> UNKNOWN
            }
        }
        "textCard" -> {
            when (dataType) {
                "TextCardWithRightAndLeftTitle", "header8" -> TEXT_CARD_HEADER8
                "header7" -> TEXT_CARD_HEADER7
                else -> UNKNOWN
            }
        }
        "banner" -> {
            when (dataType) {
                "Banner" -> BANNER
                else -> UNKNOWN
            }
        }
        "videoSmallCard" -> {
            when (dataType) {
                "VideoBeanForClient" -> Const.ItemViewType.VIDEO_SMALL_CARD
                else -> UNKNOWN
            }
        }
        "briefCard" -> {
            when (dataType) {
                "TagBriefCard" -> TAG_BRIEFCARD
                else -> UNKNOWN
            }
        }
        else -> UNKNOWN
    }

    fun getViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        SPECIAL_SQUARE_CARD_COLLECTION -> SpecialSquareCardCollectionViewHolder(R.layout.main_layout_special_square_card_collection.inflate(parent))
        else -> EmptyViewHolder(View(parent.context))
    }
}