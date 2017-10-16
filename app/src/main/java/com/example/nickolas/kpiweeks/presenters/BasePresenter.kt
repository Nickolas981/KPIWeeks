package com.example.nickolas.kpiweeks.presenters


import com.example.nickolas.kpiweeks.views.BaseView

import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BasePresenter<T : BaseView> {

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
