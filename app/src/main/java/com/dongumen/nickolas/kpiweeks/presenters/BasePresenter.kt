package com.dongumen.nickolas.kpiweeks.presenters


import com.arellomobile.mvp.MvpPresenter
import com.dongumen.nickolas.kpiweeks.views.BaseView

import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BasePresenter<T : BaseView> : MvpPresenter<T> {

    var view: T? = null

    private val compositeSubscription = CompositeSubscription()

    fun destroy() {
        compositeSubscription.clear()
    }

    protected fun subscribe(subscription: Subscription): Subscription {
        compositeSubscription.add(subscription)
        return subscription
    }
}
