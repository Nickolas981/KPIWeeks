package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation

import com.arellomobile.mvp.InjectViewState
import com.dongumen.nickolas.kpiweeks.global.presentation.BasePresenter
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.logic.GetGroupsUseCase
import org.koin.standalone.inject

@InjectViewState
class GroupSearchPresenter : BasePresenter<GroupSearchView>() {

    private val getGroupsUseCase by inject<GetGroupsUseCase>()

    fun getGroups(query: String) {
        getGroupsUseCase.get(query) {
            viewState.showPredictions(it)
        }
    }
}