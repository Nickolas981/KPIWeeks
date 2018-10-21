package com.dongumen.nickolas.kpiweeks.pages.week.presentation

import com.dongumen.nickolas.kpiweeks.global.liteMoxy.MvpPresenter
import com.dongumen.nickolas.kpiweeks.views.WeekEvents

class WeekPresenter : MvpPresenter<WeekEvents>() {
//    private val source: IWeekDataSource by inject()

    fun getSchedule(group: String, week: Int) {
//        Log.d("WeekPresenter", "group:$group\tweek:$week")
//        launch(CommonPool) {
//            source.getSchedule(group)
//                    .let { it[week] }.getAsList()
//                    .run { launch(UI) { viewState.showSchedule(this@run) } }
    }
}
