package com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence.web

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence.GroupsDataSource
import com.dongumen.nickolas.kpiweeks.persistence.web.api.KpiApi

class RetrofitGroupsDataSource(private val api: KpiApi) : GroupsDataSource {
    override suspend fun getGroups(query: String): List<Group> {
        return api.searchGroup(query).await()
                .body()
                ?.results ?: emptyList()
    }
}