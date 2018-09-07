package com.dongumen.nickolas.kpiweeks.views

import com.dongumen.nickolas.kpiweeks.model.enteties.groups.Group


interface GroupSearchView  : BaseView{
    fun showPredictions(groups : List<Group>)
}