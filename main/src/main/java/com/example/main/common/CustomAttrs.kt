package com.example.main.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.base.customview.CustomFontTextView
import com.example.base.utils.DensityUtil
import com.example.main.R
import com.example.main.utils.DateUtil

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, url: String) {
    if (url.isNotEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

fun ImageView.load(url: String, corner: Float = 0f) {
    if (corner == 0f) {
        Glide.with(this)
            .load(url)
            .into(this)
    } else {
        Glide.with(this)
            .load(url)
            .apply(RequestOptions().transform(RoundedCorners(DensityUtil.dp2px(corner))))
            .placeholder(R.drawable.main_shape_top_categories)
            .into(this)
    }
}

@BindingAdapter("pushPageImageFromUrl")
fun bindPushFragmentImageFromUrl(view: ImageView, url: String) {
    if (url.isEmpty() or (url == "http://img.wdjimg.com/image/video/418d281e65bf010c38c7b07bdd7b6a94_0_0.png")) {
        Glide.with(view.context)
            .load(R.mipmap.main_ic_launcher)
            .into(view)
    }
}

@BindingAdapter("dateMillis2Text")
fun makeDateMillis2Text(tv: CustomFontTextView, dateMillis: Long) {
    val date = DateUtil.getConvertedDate(dateMillis)
    tv.text = date
}
