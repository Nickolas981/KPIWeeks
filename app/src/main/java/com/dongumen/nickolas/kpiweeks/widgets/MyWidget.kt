package com.dongumen.nickolas.kpiweeks.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import com.dongumen.nickolas.kpiweeks.App
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.activities.ScheduleActivity
import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils
import com.dongumen.nickolas.kpiweeks.widgets.adapters.ListAdapter
import javax.inject.Inject


open class MyWidget : AppWidgetProvider() {


    //
    @Inject
    lateinit var shared: SharedPreferenceUtils
    @Inject
    lateinit var dayInfo: DayInformationUtil
    @Inject
    lateinit var parser: ResponseToScheduleUtil

    lateinit var view: RemoteViews
    lateinit var appWM: AppWidgetManager
    lateinit var appWI: IntArray
    lateinit var c: Context
    private val MyOnClick = "myOnClickTag"
    private lateinit var adapter: ListAdapter

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        App.utilsComponent().inject(this)
        adapter = ListAdapter()
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager,
                          appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        App.utilsComponent().inject(this)
        appWM = appWidgetManager
        appWI = appWidgetIds
        c = context
        view = RemoteViews(context.packageName, R.layout.my_widget)
        setClickListeners()
        update()
    }


    private fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (MyOnClick == intent?.action) {
            update()
        }
    }

    private fun update() {
        App.utilsComponent().inject(this)
        val str = shared.getStringValue("json", "")

        if (str != "") {
            view.setViewVisibility(R.id.list_view, View.VISIBLE)
            view.setViewVisibility(R.id.error_message, View.GONE)
            view.setTextViewText(R.id.day_and_date, getNormalDay(str).dayName)
        } else {
            view.setViewVisibility(R.id.list_view, View.GONE)
            view.setViewVisibility(R.id.error_message, View.VISIBLE)
        }
        appWM.updateAppWidget(appWI[0], view)
    }

    fun getNormalDay(str: String): Day {
        var number = dayInfo.scrollTo()
        var weekNumber = dayInfo.getWeekNumber()
        var week = parser.parseFromSharedPreference(str)[weekNumber - 1]

        while (number != 8) {
            if (week.days[number].lessons?.isNotEmpty()!!) {
                return week.days[number]
            }
            number++
        }
        week = parser.parseFromSharedPreference(str)[anotherWeek(number) - 1]
        number = 0
        while (number != 7) {
            if (week.days[number].lessons?.isNotEmpty()!!) {
                return week.days[number]
            }
            number++
        }
        return week.days[0]
    }

    private fun anotherWeek(week: Int): Int = if (week == 1) 2 else 1

    private fun setClickListeners() {
        view.setOnClickPendingIntent(R.id.day_and_date,
                getPendingSelfIntent(c, MyOnClick))
        val intent = Intent(c, ScheduleActivity::class.java)
        view.setOnClickPendingIntent(R.id.frame_layout, PendingIntent.getActivity(c, 1, intent, 0))
    }

}
