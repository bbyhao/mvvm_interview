package com.byy.mvvm_interviewtest.data.remote

import com.byy.mvvm_interviewtest.data.RateEntry
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Created by fq on 2018/4/28.
 */
interface ApiService {
    @GET("latest")
    fun getRate(): Observable<RateEntry>
}