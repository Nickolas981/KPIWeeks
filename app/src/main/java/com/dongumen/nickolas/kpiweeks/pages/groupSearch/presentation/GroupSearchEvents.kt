package com.dongumen.nickolas.kpiweeks.pages.groupSearch.presentation

import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group


sealed class GroupSearchEvents {
    data class ShowPredictions(val groups: List<Group>) : GroupSearchEvents()
    object OpenSchedule : GroupSearchEvents()
}