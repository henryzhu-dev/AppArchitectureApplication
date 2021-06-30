package com.zhl.baselibrary.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.zhl.baselibrary.R

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   : 图片加载类
 */
object CommonImageLoadUtil {

    fun loadImageWithContext(context: Context, imageView: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.mipmap.ic_common_placeholder)
            .error(R.mipmap.ic_common_placeholder)
            .into(imageView);
    }

    fun loadImageWithActivity(activity: Activity, imageView: ImageView, url: String) {
        Glide.with(activity)
            .load(url)
            .placeholder(R.mipmap.ic_common_placeholder)
            .error(R.mipmap.ic_common_placeholder)
            .into(imageView);
    }

    fun loadImageWithFragment(fragment: Fragment, imageView: ImageView, url: String) {
        Glide.with(fragment)
            .load(url)
            .placeholder(R.mipmap.ic_common_placeholder)
            .error(R.mipmap.ic_common_placeholder)
            .into(imageView);
    }

    fun loadImageWithView(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .placeholder(R.mipmap.ic_common_placeholder)
            .error(R.mipmap.ic_common_placeholder)
            .into(imageView);
    }

}