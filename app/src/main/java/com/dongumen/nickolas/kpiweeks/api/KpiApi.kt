package com.dongumen.nickolas.kpiweeks.api

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface KpiApi {

    @GET("groups")
    fun searchGroup(@Query("search") str: String) : Deferred<Response<List<String>>>

    @GET("groups/{group}/timetable")
    fun getTimetable(@Path("group") group : String) : Observable<ResponseBody>
}