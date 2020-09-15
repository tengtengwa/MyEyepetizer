package com.example.main.notification.push

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.base.customview.CustomFontTextView
import com.example.main.utils.DateUtil

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(imageView: ImageView, url: String) {
    if (url.isNotEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("dateMillis2Text")
fun makeDateMillis2Text(tv: CustomFontTextView, dateMillis: Long) {
    val date = DateUtil.getConvertedDate(dateMillis)
    tv.text = date
}
