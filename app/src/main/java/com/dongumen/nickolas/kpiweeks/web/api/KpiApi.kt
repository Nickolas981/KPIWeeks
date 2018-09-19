package com.dongumen.nickolas.kpiweeks.web.api

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.GroupResponse
import com.dongumen.nickolas.kpiweeks.web.GROUPS
import com.dongumen.nickolas.kpiweeks.web.TIMETABLE
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KpiApi {
    @GET(GROUPS)
    fun searchGroup(@Query("search") search: String,
                    @Query("limit") limit: Int = 30)
            : Deferred<Response<GroupResponse>>

    @GET(TIMETABLE)
    fun getTimetable(@Path("group") group: String): Deferred<ResponseBody>
}