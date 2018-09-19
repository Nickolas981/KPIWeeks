package com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence

class GroupsRepositoryImpl(private val groupsDataSoure: GroupsDataSoure) : GroupsRepository {
    override suspend fun getGroups(query: String) = groupsDataSoure.getGroups(query)
}