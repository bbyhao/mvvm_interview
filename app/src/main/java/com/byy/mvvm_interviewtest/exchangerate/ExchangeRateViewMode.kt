package com.byy.mvvm_interviewtest.exchangerate

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import com.byy.mvvm_interviewtest.BaseViewModel
import com.byy.mvvm_interviewtest.data.RateEntry
import com.byy.mvvm_interviewtest.data.RateItem
import com.byy.mvvm_interviewtest.data.Rates
import com.byy.mvvm_interviewtest.data.localData
import com.byy.mvvm_interviewtest.data.remote.Network
import com.byy.mvvm_interviewtest.util.Preference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Created by fq on 2018/4/28.
 */
class ExchangeRateViewMode(var context: Application) : BaseViewModel(context) {
    var item = ObservableField<List<RateItem>>(ArrayList())
    var loading = ObservableBoolean(false)
    var mOrder by Preference(context, ExchangeRateActivity.Rate_ORDER, "")

    fun update() {
        loading.set(true)
        Network.getApi().getRate().timeout(5, TimeUnit.SECONDS)
                .onErrorResumeNext { e: Throwable -> e.printStackTrace()
                    Observable.just(Gson().fromJson(localData, RateEntry::class.java))
                }
                .compose(bindToLifeCycle())
                .subscribeOn(Schedulers.io())
                .subscribe({ handlerResult(it) }, {
                    loading.set(false)
                    it.printStackTrace()
                }, {
                    loading.set(false)
                })
    }

    private fun handlerResult(rate: RateEntry) {
        val list = ArrayList<RateItem>()
        val fields = Rates::class.java.declaredFields
        fields.forEach {
            it.isAccessible = true
            val t = it.get(rate.rates) as Double
            list.add(RateItem(it.name.toUpperCase(), t, t.toString()))
        }
        if (!TextUtils.isEmpty(mOrder)) {
            val map = Gson().fromJson<Map<String, Int>>(mOrder, object : TypeToken<Map<String, Int>>() {}.type)
            list.sortWith(Comparator { o1, o2 ->
                map[o1.country]?.minus(map[o2.country] ?: Int.MAX_VALUE) ?: 0
            })
        }
        item.set(list)
    }


}
