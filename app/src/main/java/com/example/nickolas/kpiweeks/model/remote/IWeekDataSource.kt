package com.example.nickolas.kpiweeks.model.remote

import android.app.Activity
import android.content.Context
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Nickolas on 16.10.2017.
 */
interface IWeekDataSource {
    fun getSchedule(group : String, context: Activity) : Observable<ResponseBody>
}