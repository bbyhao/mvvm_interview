package com.byy.mvvm_interviewtest.html

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.byy.mvvm_interviewtest.GlideApp
import com.byy.mvvm_interviewtest.R

/**
 * @author Created by fq on 2018/4/27.
 */

object ImageUrlBinding{

    @BindingAdapter("app:url")
    @JvmStatic fun setUrl(v: ImageView,url:String ){
        GlideApp.with(v).load(url)
                .centerInside()
                .placeholder(R.mipmap.ic_launcher_round)
                .frame(500)
                .error(R.drawable.ic_launcher_background)
                .into(v)
    }
}