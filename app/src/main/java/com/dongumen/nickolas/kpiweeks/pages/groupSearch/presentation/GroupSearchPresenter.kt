package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation

import com.dongumen.nickolas.kpiweeks.global.group.GroupManager
import com.dongumen.nickolas.kpiweeks.global.liteMoxy.MvpPresenter
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.logic.GetGroupsUseCase
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import org.koin.standalone.inject

class GroupSearchPresenter : MvpPresenter<GroupSearchEvents>() {

    private val getGroupsUseCase by inject<GetGroupsUseCase>()
    private val groupManager by inject<GroupManager>()


    fun getGroups(query: String) {
        getGroupsUseCase.get(query) {
            view.update(GroupSearchEvents.ShowPredictions(it))
        }
    }

    fun setGroup(group: Group) {
        groupManager.group = group
        view.update(GroupSearchEvents.OpenSchedule)
    }
}