package com.example.nickolas.kpiweeks.model.remote

import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Nickolas on 15.10.2017.
 */
interface  ISearchDataSource{
    fun getGroups(str : String) : Observable<ResponseBody>
}