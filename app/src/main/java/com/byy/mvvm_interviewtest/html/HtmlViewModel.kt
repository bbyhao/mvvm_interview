package com.byy.mvvm_interviewtest.html

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.byy.mvvm_interviewtest.data.HtmlRepository

/**
 * @author Created by fq on 2018/4/27.
 */
class HtmlViewModel (private val context: Application) : AndroidViewModel(context) {

    private val repository=HtmlRepository(context)
    val liveData = MutableLiveData<String>()
      var url= ObservableField<String>("https://b-ssl.duitang.com/uploads/item/201412/19/20141219231048_zVF4d.thumb.700_0.jpeg")

    fun parseDesImage(url: String){
        repository.getImageData(url,liveData)
    }
}