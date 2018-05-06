package com.byy.mvvm_interviewtest.data.remote

import com.byy.mvvm_interviewtest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Created by fq on 2018/4/28.
 */
object Network {

    fun getApi(): ApiService {retrofit.baseUrl()
        return retrofit.create(ApiService::class.java)

    }

    private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
}