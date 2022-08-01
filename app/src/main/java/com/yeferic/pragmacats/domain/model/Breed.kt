package com.yeferic.pragmacats.domain.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yeferic.pragmacats.R

data class Breed(
    var name: String,
    var intelligence: Int,
    var image: Image,
    var description: String,
    var origin: String
)
