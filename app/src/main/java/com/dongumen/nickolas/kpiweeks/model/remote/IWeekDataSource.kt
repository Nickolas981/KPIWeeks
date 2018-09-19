package com.dongumen.nickolas.kpiweeks.model.remote

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody


interface IWeekDataSource {
    fun getSchedule(group: String): Deferred<ResponseBody>
}