package com.example.nickolas.kpiweeks.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Nickolas on 15.10.2017.
 */
interface KpiApi {

    @GET("groups.json")
    fun searchGroup(@Query("search") str: String) : Observable<ResponseBody>

    @GET("groups/{group}/timetable")
    fun getTimetable(@Path("group") group : String) : Observable<ResponseBody>
}