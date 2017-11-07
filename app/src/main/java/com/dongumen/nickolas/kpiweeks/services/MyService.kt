package com.dongumen.nickolas.kpiweeks.services

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.RemoteViewsService
import com.dongumen.nickolas.kpiweeks.widgets.adapters.MyFactory

@SuppressLint("Registered")


class MyService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return MyFactory(applicationContext, intent)
    }

}