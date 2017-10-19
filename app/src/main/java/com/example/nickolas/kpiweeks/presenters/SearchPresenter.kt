package com.example.nickolas.kpiweeks.presenters

import com.example.nickolas.kpiweeks.model.remote.ISearchDataSource
import com.example.nickolas.kpiweeks.model.remote.SearchDataSource
import com.example.nickolas.kpiweeks.utils.ResponseToGroupList
import com.example.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.example.nickolas.kpiweeks.views.GroupSearchView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Nickolas on 15.10.2017.
 */
class SearchPresenter(val source: ISearchDataSource) : BasePresenter<GroupSearchView>() {
    fun getGroups(str: String) {

        subscribe(source.getGroups(str)
                .retryWhen(RxRetryWithDelay())
                .map<Map<String, String>> { ResponseToGroupList.parse(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showPredictions(it) })
        )

    }
}