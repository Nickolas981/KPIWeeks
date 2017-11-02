package com.dongumen.nickolas.kpiweeks.utils.rx

import android.content.Context
import android.widget.Toast
import com.dongumen.nickolas.kpiweeks.R
import rx.functions.Action1


class RxErrorAction(private val context: Context) : Action1<Throwable> {

    override fun call(throwable: Throwable) {
        throwable.printStackTrace()

        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show()
    }

}