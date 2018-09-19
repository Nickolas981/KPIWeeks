package com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group

interface GroupsDataSoure {
    suspend fun getGroups(query: String): List<Group>
}