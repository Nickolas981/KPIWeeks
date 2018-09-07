package com.dongumen.nickolas.kpiweeks.presenters

import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.dongumen.nickolas.kpiweeks.views.WeekView
import org.koin.standalone.inject
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class WeekPresenter: BasePresenter<WeekView>() {
    private val source: IWeekDataSource by inject()
    private val responseToScheduleUtil: ResponseToScheduleUtil by inject()


    fun getSchedule(group: String, week: Int) {
        subscribe(source.getSchedule(group)
                .retryWhen(RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .map<MutableList<Week>> { responseToScheduleUtil.parse(it) }
                .map { it[week].getAsList()  }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view?.showSchedule(it) })
    }

    fun deleteItem(week: Int, day: Int, lesson: Int) {
        responseToScheduleUtil.delete(week, day, lesson)
    }
}