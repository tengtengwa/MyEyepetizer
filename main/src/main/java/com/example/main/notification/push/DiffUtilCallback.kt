package com.example.main.notification.push

import androidx.recyclerview.widget.DiffUtil
import com.example.main.logic.model.PushMessage

/**
 * 通过这个类来计算Adapter数据改变的item，以进行局部刷新。此过程在主线程执行，但PushFragment数据量不大，
 * 如果数据量很大，需要使用AsyncListDiffer将计算过程放到子线程执行，或者直接继承ListAdapter，可以参考
 * https://developer.android.google.cn/reference/kotlin/androidx/recyclerview/widget/AsyncListDiffer
 */
class DiffUtilCallback(
    private val oldList: List<PushMessage.Message>,
    private val newList: List<PushMessage.Message>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].date == newList[newItemPosition].date

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].content == newList[newItemPosition].content
}