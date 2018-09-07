package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.model.enteties.groups.GroupResponse
import com.dongumen.nickolas.kpiweeks.web.api.KpiApi
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response


class SearchDataSource(private val api: KpiApi) : ISearchDataSource {
    override fun getGroups(str: String): Deferred<Response<GroupResponse>> = api.searchGroup(str)
}