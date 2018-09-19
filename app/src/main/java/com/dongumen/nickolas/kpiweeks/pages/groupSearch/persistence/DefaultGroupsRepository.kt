package com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence

class DefaultGroupsRepository(private val groupsDataSource: GroupsDataSource) : GroupsRepository {
    override suspend fun getGroups(query: String) = groupsDataSource.getGroups(query)
}