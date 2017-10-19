package com.example.nickolas.kpiweeks.presenters

import com.example.nickolas.kpiweeks.model.enteties.Week
import com.example.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.example.nickolas.kpiweeks.utils.ResponseToSchedule
import com.example.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.example.nickolas.kpiweeks.views.WeekView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class WeekPresenter(val source: IWeekDataSource) : BasePresenter<WeekView>() {
    fun getSchedule(group: String, week: Int) {
        subscribe(source.getSchedule(group)
                .retryWhen(RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .map<MutableList<Week>>{ResponseToSchedule.parse(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({view?.showSchedule(it[week])}))
    }

}