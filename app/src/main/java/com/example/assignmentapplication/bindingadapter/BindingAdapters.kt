package com.example.assignmentapplication.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.assignmentapplication.R

@BindingAdapter("avatar")
fun ImageView.imageFromUrl(url: String) {
    Glide.with(this.context).load(url).placeholder(R.drawable.demo_avatar).into(this)
}