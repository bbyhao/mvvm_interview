package com.byy.mvvm_interviewtest.exchangerate

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.byy.mvvm_interviewtest.data.RateItem

/**
 * @author Created by fq on 2018/4/28.
 */
object itemRateBinding {

    @BindingAdapter("app:items")
    @JvmStatic fun setItem(v: RecyclerView, data:List<RateItem>){
        val adapter = v.adapter as RateAdapter
        adapter.notifyData(data)
    }
}

@BindingAdapter("android:onRefresh")
fun SwipeRefreshLayout.setSwipeRefreshLayoutOnRefreshListener(viewMode: ExchangeRateViewMode){
    setOnRefreshListener{ viewMode.update()}
}