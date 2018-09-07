package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.api.KpiApi
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable


class SearchDataSource(val api : KpiApi) : ISearchDataSource {
    override fun getGroups(str: String) : Deferred<Response<List<String>>> = api.searchGroup(str)
}