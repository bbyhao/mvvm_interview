package com.byy.mvvm_interviewtest.data

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.text.TextUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import org.jsoup.Jsoup

/**
 * @author Created by fq on 2018/4/28.
 */
class HtmlRepository(private val context: Context) {

    fun getImageData(url: String,liveData:MutableLiveData<String>): MutableLiveData<String> {

        doAsync({
            it.printStackTrace()
            context.runOnUiThread {
                context.toast(it.message ?: "未知错误")
            }
            liveData.postValue(null)
        }, {
            val doc = Jsoup.connect(url).get()
            val elements = doc.getElementsByAttributeValueContaining("rel", "apple-touch-icon-")
            for (element in elements) {
                val imgUrl = element.attr("href")
                if (!TextUtils.isEmpty(imgUrl)) {
                    liveData.postValue(imgUrl)
                }
                return@doAsync
            }
            liveData.postValue("")
        })

        return liveData
    }


}