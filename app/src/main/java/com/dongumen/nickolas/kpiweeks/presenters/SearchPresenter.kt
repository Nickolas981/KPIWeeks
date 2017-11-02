package com.dongumen.nickolas.kpiweeks.presenters

import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource
import com.dongumen.nickolas.kpiweeks.utils.ResponseToGroupListUtil
import com.dongumen.nickolas.kpiweeks.utils.rx.RxErrorAction
import com.dongumen.nickolas.kpiweeks.utils.rx.RxRetryWithDelay
import com.dongumen.nickolas.kpiweeks.views.GroupSearchView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Nickolas on 15.10.2017.
 */
class SearchPresenter(val source: ISearchDataSource) : BasePresenter<GroupSearchView>() {
    fun getGroups(str: String) {
        subscribe(source.getGroups(str)
                .retryWhen(RxRetryWithDelay())
                .map<List<String>> { ResponseToGroupListUtil.parse(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showPredictions(it) }, {RxErrorAction(view?.context!!)})
        )
    }
}