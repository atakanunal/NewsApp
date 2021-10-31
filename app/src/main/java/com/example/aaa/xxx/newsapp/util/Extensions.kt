package com.example.aaa.xxx.newsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:downloadImage")
fun ImageView.downloadImage(url:String){
    Glide.with(context)
        .load(url)
        .into(this)
}