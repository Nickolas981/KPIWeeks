package com.dongumen.nickolas.kpiweeks.utils.rx

import android.content.Context
import android.widget.Toast
import com.dongumen.nickolas.kpiweeks.R
import rx.functions.Action1


class RxErrorAction : Action1<Throwable> {

    override fun call(throwable: Throwable) {
        throwable.printStackTrace()
    }

}