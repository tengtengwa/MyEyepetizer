package com.example.main.common

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.example.base.bean.Const.ItemViewType.BANNER
import com.example.base.bean.Const.ItemViewType.COLUMN_CARD_LIST
import com.example.base.bean.Const.ItemViewType.HORIZONTAL_SCROLL_CARD
import com.example.base.bean.Const.ItemViewType.SPECIAL_SQUARE_CARD_COLLECTION
import com.example.base.bean.Const.ItemViewType.TAG_BRIEFCARD
import com.example.base.bean.Const.ItemViewType.TEXT_CARD_HEADER7
import com.example.base.bean.Const.ItemViewType.TEXT_CARD_HEADER8
import com.example.base.bean.Const.ItemViewType.UNKNOWN
import com.example.base.bean.Const.ItemViewType.VIDEO_SMALL_CARD
import com.example.base.customview.CustomFontTextView
import com.example.base.customview.HorizontalRecyclerView
import com.example.base.utils.inflate
import com.example.main.R
import com.example.main.home.discovery.DiscoveryAdapter
import com.example.main.logic.model.Discovery
import com.zhpan.bannerview.BannerViewPager


/**
 * 最上面的banner
 */
class HorizontalScrollCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val bannerViewPager: BannerViewPager<Discovery.ItemX, DiscoveryAdapter.HorizontalScrollCardAdapter.ViewHolder> =
        view.findViewById(R.id.banner_viewpager)
}

/**
 * 首页-发现中热门分类的ViewHolder
 */
class SpecialSquareCardCollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val rightText: CustomFontTextView = view.findViewById(R.id.tv_right)

    val group: Group = view.findViewById(R.id.gp_top_categories)

    val recyclerView: HorizontalRecyclerView = view.findViewById(R.id.hrv_top_categories_list)
}

/**
 * 空ViewHolder
 */
class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

/**
 * 首页-发现中专题策划的ViewHolder
 */
class SquareCardOfColumnViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val rightText: CustomFontTextView = view.findViewById(R.id.tv_right)

    val group: Group = view.findViewById(R.id.gp_theme_classification)

    val recyclerView: RecyclerView = view.findViewById(R.id.rv_theme_classification)
}

/**
 * 开眼专栏的Header
 */
class TextCardHeader7ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val rightText: CustomFontTextView = view.findViewById(R.id.tv_right)

    val topDivider: View = view.findViewById(R.id.top_divider)
}

/**
 * 开眼专栏的banner
 */
class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.iv)
}

/**
 * 本周榜单的Header
 */
class TextCardHeader8ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val rightText: CustomFontTextView = view.findViewById(R.id.tv_right)
}

/**
 * 本周榜单的视频卡片
 */
class VideoSmallCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: CustomFontTextView = view.findViewById(R.id.tv_title)

    val videoCover: ImageView = view.findViewById(R.id.iv_video_cover)

    val videoDuration: CustomFontTextView = view.findViewById(R.id.tv_video_duration)

    val category: CustomFontTextView = view.findViewById(R.id.tv_category)

    val imageMore: ImageView = view.findViewById(R.id.iv_more)
}

/**
 * 推荐主题下的简洁卡片
 */
class BriefCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val icon: ImageView = view.findViewById(R.id.iv_icon)

    val title :CustomFontTextView = view.findViewById(R.id.tv_title)

    val description: CustomFontTextView = view.findViewById(R.id.tv_description)

    val plusFollow: CustomFontTextView = view.findViewById(R.id.tv_plus_follow)
}

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
                "VideoBeanForClient" -> VIDEO_SMALL_CARD
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

        HORIZONTAL_SCROLL_CARD -> HorizontalScrollCardViewHolder(R.layout.main_layout_horizontal_scroll_card.inflate(parent))

        SPECIAL_SQUARE_CARD_COLLECTION -> SpecialSquareCardCollectionViewHolder(R.layout.main_layout_special_square_card_collection.inflate(parent))

        COLUMN_CARD_LIST -> SquareCardOfColumnViewHolder(R.layout.main_layout_sqaure_card_column.inflate(parent))

        TEXT_CARD_HEADER7 -> TextCardHeader7ViewHolder(R.layout.main_layout_textcard_header7.inflate(parent))

        BANNER -> BannerViewHolder(R.layout.main_layout_banner.inflate(parent))

        TEXT_CARD_HEADER8 -> TextCardHeader8ViewHolder(R.layout.main_layout_textcard_header8.inflate(parent))

        VIDEO_SMALL_CARD -> VideoSmallCardViewHolder(R.layout.main_layout_video_small_card.inflate(parent))

        TAG_BRIEFCARD -> BriefCardViewHolder(R.layout.main_layout_brief_card.inflate(parent))

        else -> EmptyViewHolder(View(parent.context))
    }
}