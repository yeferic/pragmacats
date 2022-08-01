package com.yeferic.pragmacats.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yeferic.pragmacats.R

fun ImageView.loadImageFromUrl(url : String){
    Glide.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.no_image)
        .fitCenter()
        .into(this)
}