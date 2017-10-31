package com.example.nickolas.kpiweeks.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface KpiApi {

    @GET("groups")
    fun searchGroup(@Query("search") str: String) : Observable<ResponseBody>

    @GET("groups/{group}/timetable")
    fun getTimetable(@Path("group") group : String) : Observable<ResponseBody>
}