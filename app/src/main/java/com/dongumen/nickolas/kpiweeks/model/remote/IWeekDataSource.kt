package com.dongumen.nickolas.kpiweeks.model.remote

import okhttp3.ResponseBody
import rx.Observable


interface IWeekDataSource {
    fun getSchedule(group: String): Observable<ResponseBody>
}