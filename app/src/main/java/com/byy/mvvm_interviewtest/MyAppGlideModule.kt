package com.byy.mvvm_interviewtest

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule

/**
 * @author Created by fq on 2018/4/27.
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setDiskCache(InternalCacheDiskCacheFactory(context,
                "IMAGE_CACHE",2500L*1024*1024
                ))
    }
}