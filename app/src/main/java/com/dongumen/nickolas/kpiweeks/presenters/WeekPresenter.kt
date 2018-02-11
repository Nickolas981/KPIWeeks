package com.dongumen.nickolas.kpiweeks.presenters

import com.dongumen.nickolas.kpiweeks.App
import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.dongumen.nickolas.kpiweeks.views.WeekView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject


class WeekPresenter(val source: IWeekDataSource)
    : BasePresenter<WeekView>() {

    @Inject
    lateinit var responseToScheduleUtil: ResponseToScheduleUtil

    init {
        App.utilsComponent().inject(this)
    }

    fun getSchedule(group: String, week: Int) {
        subscribe(source.getSchedule(group)
                .retryWhen(RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .map<MutableList<Week>> { responseToScheduleUtil.parse(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showSchedule(it[week]) }))
    }

    fun deleteItem(week: Int, day: Int, lesson: Int) {
        responseToScheduleUtil.delete(week, day, lesson)
    }
}