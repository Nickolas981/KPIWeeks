package com.example.nickolas.kpiweeks.model.remote

import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Nickolas on 16.10.2017.
 */
interface IWeekDataSource {
    fun getSchedule(group : String) : Observable<ResponseBody>
}