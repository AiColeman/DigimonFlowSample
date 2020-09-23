package com.github.aicoleman.digimon_flow_sample.util

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imgLoad")
fun bindImgLoad(view: ShapeableImageView, url: String?) {
    val context = view.context
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(view)
}