package com.dongumen.nickolas.kpiweeks.model.remote

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable

/**
 * Created by Nickolas on 15.10.2017.
 */
interface  ISearchDataSource{
    fun getGroups(str : String) : Deferred<Response<List<String>>>
}