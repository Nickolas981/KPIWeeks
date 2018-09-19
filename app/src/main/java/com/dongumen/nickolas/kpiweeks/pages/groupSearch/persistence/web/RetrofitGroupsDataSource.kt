package com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence.web

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence.GroupsDataSoure
import com.dongumen.nickolas.kpiweeks.web.api.KpiApi

class RetrofitGroupsDataSource(private val api: KpiApi) : GroupsDataSoure {
    override suspend fun getGroups(query: String): List<Group> {
        return api.searchGroup(query).await()
                .body()
                ?.results ?: emptyList()
    }
}