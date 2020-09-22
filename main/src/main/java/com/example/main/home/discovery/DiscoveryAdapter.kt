package com.example.main.home.discovery

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.base.customview.CustomFontTextView
import com.example.base.utils.inflate
import com.example.base.utils.setAllOnClickListener
import com.example.base.utils.toast
import com.example.main.R
import com.example.main.common.*
import com.example.main.logic.model.Discovery
import com.example.main.notification.push.load

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
                    title.text = itemData.data.header.title
                    rightText.text = itemData.data.header.rightText
                }
            }
            is BannerViewHolder -> {
                holder.apply {
                    image.load(itemData.data.image, 4f)
                }
            }
            is TextCardHeader8ViewHolder -> {
                holder.apply {
                    title.text = itemData.data.header.title
                    rightText.text = itemData.data.header.rightText
                }
            }
        }
        //TODO("暂未完全完成")
    }

    override fun getItemCount() = dataList.size


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