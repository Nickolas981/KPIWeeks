package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation

import com.dongumen.nickolas.kpiweeks.global.presentation.BaseView
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group


interface GroupSearchView : BaseView {
    fun showPredictions(groups : List<Group>)
}