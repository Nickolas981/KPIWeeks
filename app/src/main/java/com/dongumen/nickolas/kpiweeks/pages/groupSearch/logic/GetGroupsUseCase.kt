package com.dongumen.nickolas.kpiweeks.pages.groupSearch.logic

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.persistence.GroupsRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class GetGroupsUseCase(private val repository: GroupsRepository) {
    fun get(query: String, callback: (List<Group>) -> Unit) {
        launch {
            repository.getGroups(query).let { launch(UI) { callback.invoke(it) } }
        }
    }
}
