package com.example.main.home.discovery

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.base.StartService
import com.example.base.customview.CustomFontTextView
import com.example.base.utils.DensityUtil.dp2px
import com.example.base.utils.inflate
import com.example.base.utils.logD
import com.example.base.utils.setAllOnClickListener
import com.example.base.utils.toast
import com.example.main.R
import com.example.main.common.*
import com.example.main.home.daily.DailyAdapter.Companion.DAILY_LIBRARY_TYPE
import com.example.main.logic.model.Discovery
import com.example.main.common.load
import com.example.main.utils.DateUtil
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class DiscoveryAdapter(
    private val fragment: DiscoveryFragment,
    private val dataList: List<Discovery.Item>
) :
    ListAdapter<Discovery.Item, RecyclerView.ViewHolder>(DiscoveryDiffCallback()) {

    override fun getItemViewType(position: Int): Int =
        RecyclerViewHelper.getItemViewType(dataList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHelper.getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = dataList[position]
        when (holder) {
            is HorizontalScrollCardViewHolder -> {
                holder.bannerViewPager.apply {
                    adapter = HorizontalScrollCardAdapter()
                    setAutoPlay(true)
                    setIndicatorVisibility(View.VISIBLE)
                    setIndicatorSliderColor(Color.GRAY, Color.WHITE)
                    setIndicatorSliderRadius(dp2px(2f))
                    setCanLoop(true)
                    setRoundCorner(dp2px(4f))
                    setRevealWidth(dp2px(14f))
                    if (itemData.data.itemList.size == 1) setPageMargin(0) else setPageMargin(dp2px(4f))
                    setOnClickListener {
                        //todo:处理点击事件
                    }
                    create(itemData.data.itemList)
                }
            }
            is SpecialSquareCardCollectionViewHolder -> {
                holder.apply {
                    title.text = itemData.data.header.title
                    rightText.text = itemData.data.header.rightText
                    val itemList = itemData.data.itemList
                    group.setAllOnClickListener {
                        //todo(热门分类跳转到全部分类页面)
                        "该功能暂未开放，尽请期待".toast()
                    }
                    recyclerView.adapter = SpecialSquareCardCollectionAdapter().apply {
                        submitList(itemList)
                    }
                    recyclerView.setHasFixedSize(true)
                    recyclerView.isNestedScrollingEnabled = true
                    //todo("这两个布局中的RecyclerView暂未添加ItemDecoration")
                }
            }
            is SquareCardOfColumnViewHolder -> {
                holder.apply {
                    title.text = itemData.data.header.title
                    rightText.text = itemData.data.header.rightText
                    val itemList = itemData.data.itemList
                    group.setAllOnClickListener {
                        //todo(热门分类跳转到全部分类页面)
                        "该功能暂未开放，尽请期待".toast()
                    }
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = SquareCardOfColumnAdapter().apply {
                        submitList(itemList)
                    }
                    //todo("这两个布局中的RecyclerView暂未添加ItemDecoration")
                }
            }
            is TextCardHeader7ViewHolder -> {
                holder.apply {
                    title.text = itemData.data.text
                    rightText.text = itemData.data.rightText
                    if (title.text.toString() == "推荐主题") topDivider.visibility = View.VISIBLE
                }
            }
            is BannerViewHolder -> {
                holder.apply {
                    image.load(itemData.data.image, 4f)
                }
            }
            is TextCardHeader8ViewHolder -> {
                holder.apply {
                    title.text = itemData.data.text
                    rightText.text = itemData.data.rightText
                }
            }
            is VideoSmallCardViewHolder -> {
                holder.apply {
                    title.text = itemData.data.title
                    videoCover.load(itemData.data.cover.feed, 4f)
                    videoDuration.text = DateUtil.convertVideoDuration(itemData.data.duration)
                    category.text =
                        if (itemData.data.library == DAILY_LIBRARY_TYPE) "#${itemData.data.category} / 开眼精选" else "#${itemData.data.category}"
                    imageMore.setOnClickListener {
                        //todo("弹出BottomSheetFragment")
                    }
                    itemView.setOnClickListener {
                        //todo("视频播放模块待完成")
                        "此功能暂未开放，尽请期待".toast()
                    }
                }
            }
            is BriefCardViewHolder -> {
                holder.apply {
                    title.text = itemData.data.title
                    icon.load(itemData.data.icon, 4f)
                    description.text = itemData.data.description
                    plusFollow.setOnClickListener {
                        StartService.startLogin()
                    }
                }
            }
        }
    }

    override fun getItemCount() = dataList.size


    inner class HorizontalScrollCardAdapter : BaseBannerAdapter<Discovery.ItemX, HorizontalScrollCardAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : BaseViewHolder<Discovery.ItemX>(view) {

            override fun bindData(item: Discovery.ItemX, position: Int, pageSize: Int) {
                val imageBg: ImageView = findView(R.id.iv_bg)
                val label: CustomFontTextView = findView(R.id.tv_label)
                if(!item.data.label?.text.isNullOrEmpty()) label.visibility = View.VISIBLE
                imageBg.load(item.data.image, 4f)
                label.text = item.data.label?.text ?: ""
            }
        }

        override fun createViewHolder(itemView: View, viewType: Int) = ViewHolder(itemView)

        override fun onBind(holder: ViewHolder, data: Discovery.ItemX, position: Int, pageSize: Int) =
            holder.bindData(data, position, pageSize)

        //这里返回的是banner的itemview的布局
        override fun getLayoutId(viewType: Int) = R.layout.main_item_banner_item

    }

    /**
     * 热门分类下16个子项的Adapter
     */
    inner class SpecialSquareCardCollectionAdapter :
        ListAdapter<Discovery.ItemX, SpecialSquareCardCollectionAdapter.ViewHolder>(
            SquareCardDiffCallback()
        ) {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val imageBg: ImageView = view.findViewById(R.id.iv_bg)

            val title: CustomFontTextView = view.findViewById(R.id.tv_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(R.layout.main_item_special_square_card_collection.inflate(parent)).apply {
                itemView.setOnClickListener {
                    "该功能暂未开放，尽请期待".toast()
                    //todo("在这里为热门分类的子项设置点击事件，跳转到具体的分类页面")
                }
            }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemData = currentList[position]
            holder.apply {
                imageBg.load(itemData.data.image, 4f)
                title.text = itemData.data.title
            }
        }

        override fun getItemCount() = currentList.size
    }

    /**
     * 专题策划下四个itemview的Adapter
     */
    inner class SquareCardOfColumnAdapter :
        ListAdapter<Discovery.ItemX, SquareCardOfColumnAdapter.ViewHolder>(SquareCardDiffCallback()) {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val imageBg: ImageView = view.findViewById(R.id.iv_bg)

            val title: CustomFontTextView = view.findViewById(R.id.tv_title)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder =
            ViewHolder(R.layout.main_item_sqaure_card_column.inflate(parent)).apply {
                itemView.setOnClickListener {
                    "该功能暂未开放，尽请期待".toast()
                    //todo("在这里为专题策划的子项设置点击事件，跳转到具体的分类页面")
                }
            }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemData = currentList[position]
            holder.apply {
                imageBg.load(itemData.data.image, 4f)
                title.text = itemData.data.title
            }
        }

    }
}