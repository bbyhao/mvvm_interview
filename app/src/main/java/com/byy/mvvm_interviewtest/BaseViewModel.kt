package com.byy.mvvm_interviewtest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

/**
 * @author Created by fq on 2018/5/4.
 */
open class BaseViewModel( application:Application) : AndroidViewModel(application) {

   private val mLifeSubject= PublishSubject.create<Int>()!!


    open fun <T> bindToLifeCycle(): ObservableTransformer<T, T> = ObservableTransformer{
        it.takeUntil(mLifeSubject)
    }

    override fun onCleared() {
        super.onCleared()
        mLifeSubject.onNext(1)
    }
}