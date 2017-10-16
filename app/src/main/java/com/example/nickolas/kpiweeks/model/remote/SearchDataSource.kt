package com.example.nickolas.kpiweeks.model.remote

import com.example.nickolas.kpiweeks.api.KpiApi
import okhttp3.ResponseBody
import rx.Observable


class SearchDataSource(val api : KpiApi) : ISearchDataSource {
    override fun getGroups(str: String) : Observable<ResponseBody> = api.searchGroup(str)
}