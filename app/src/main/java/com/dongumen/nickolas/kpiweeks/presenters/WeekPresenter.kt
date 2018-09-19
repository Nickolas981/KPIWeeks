package com.dongumen.nickolas.kpiweeks.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.views.WeekView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.inject

@InjectViewState
class WeekPresenter : BasePresenter<WeekView>() {
    private val source: IWeekDataSource by inject()
    private val responseToScheduleUtil: ResponseToScheduleUtil by inject()


    fun getSchedule(group: String, week: Int) {
        Log.d("WeekPresenter", "group:$group\tweek:$week")
        launch(CommonPool) {
            source.getSchedule(group)
                    .await()
                    .let { responseToScheduleUtil.parse(it) }
                    .let { it[week] }.getAsList()
                    .run { launch(UI) { view?.showSchedule(this@run) } }
        }
    }

    fun deleteItem(week: Int, day: Int, lesson: Int) {
        responseToScheduleUtil.delete(week, day, lesson)
    }
}