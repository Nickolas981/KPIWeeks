package com.dongumen.nickolas.kpiweeks.presenters

import android.app.Activity
import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.dongumen.nickolas.kpiweeks.views.WeekView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class WeekPresenter(val source: IWeekDataSource) : BasePresenter<WeekView>() {
    fun getSchedule(group: String, week: Int, context : Activity) {
        subscribe(source.getSchedule(group, context)
                .retryWhen(RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .map<MutableList<Week>>{ ResponseToScheduleUtil.parse(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view?.showSchedule(it[week])}))
    }
}