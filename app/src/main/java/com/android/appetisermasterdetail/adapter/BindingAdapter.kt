package com.android.appetisermasterdetail.adapter

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.appetisermasterdetail.R
import com.android.appetisermasterdetail.helper.getParentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("contentImage")
fun loadContentImage(view: ImageView, imageUrl: String) {
    Glide.with(view)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_foreground)
        .thumbnail(0.5f)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}

@BindingAdapter("mutableTextView")
fun setMutableTextView(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}