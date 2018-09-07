package com.dongumen.nickolas.kpiweeks.utils

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
//import com.dongumen.nickolas.kpiweeks.widgets.MyWidget

/**
 * Created by Nickolas on 05.11.2017.
 */
class WidgetUpdate {
    companion object {
        fun updateWidget(context: Context) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
//            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, MyWidget::class.java))
//            if (appWidgetIds.isNotEmpty()) {
//                MyWidget().onUpdate(context, appWidgetManager, appWidgetIds)
//            }
        }
    }
}