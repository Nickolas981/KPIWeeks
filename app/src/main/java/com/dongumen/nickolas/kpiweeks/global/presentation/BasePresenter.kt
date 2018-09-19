package com.dongumen.nickolas.kpiweeks.global.presentation

import com.arellomobile.mvp.MvpPresenter
import org.koin.standalone.KoinComponent

open class BasePresenter<T : BaseView> : MvpPresenter<T>(), KoinComponent
