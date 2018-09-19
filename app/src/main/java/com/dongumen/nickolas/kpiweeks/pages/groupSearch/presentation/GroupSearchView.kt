package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import com.dongumen.nickolas.kpiweeks.views.BaseView


interface GroupSearchView : BaseView {
    fun showPredictions(groups : List<Group>)
}