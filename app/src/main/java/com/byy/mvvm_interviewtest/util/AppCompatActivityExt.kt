package com.byy.mvvm_interviewtest.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * @author Created by fq on 2018/4/27.
 */

fun <T:ViewModel> AppCompatActivity.obtainViewModel(viewModelClass:Class<T>)=
        ViewModelProviders.of(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(application)).get(viewModelClass)

fun AppCompatActivity.check(url:String):Boolean=
    url.matches("^(?:https?://)?[\\w]+(?:\\.?[\\w]+)+[\\w-_/?&=#%:]*$".toRegex())
