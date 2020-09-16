package com.example.main.notification.push

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.base.customview.CustomFontTextView
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

@BindingAdapter("pushPageImageFromUrl")
fun bindPushFragmentImageFromUrl(view: ImageView, url: String) {
    if (url.isNotEmpty()) {
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
