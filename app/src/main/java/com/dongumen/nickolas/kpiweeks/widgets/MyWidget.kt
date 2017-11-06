package com.dongumen.nickolas.kpiweeks.widgets

import android.annotation.SuppressLint
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
import javax.inject.Inject


open class MyWidget : AppWidgetProvider() {


    @Inject
    lateinit var shared: SharedPreferenceUtils
    @Inject
    lateinit var dayInfo: DayInformationUtil
    @Inject
    lateinit var parser: ResponseToScheduleUtil

    companion object {
        lateinit var view: RemoteViews
        lateinit var appWM: AppWidgetManager
        lateinit var appWI: IntArray
        @SuppressLint("StaticFieldLeak")
        lateinit var c: Context
    }

    private val MyOnClick = "myOnClickTag"

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        App.utilsComponent().inject(this)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager,
                          appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        App.utilsComponent().inject(this)
        appWM = appWidgetManager
        appWI = appWidgetIds
        c = context
        view = RemoteViews(context.packageName, R.layout.my_widget)
        update()
    }


    /*  private fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
          val intent = Intent(context, javaClass)
          intent.action = action
          return PendingIntent.getBroadcast(context, 0, intent, 0)
      }*/

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (MyOnClick == intent?.action) update()
    }

    private fun update() {
        App.utilsComponent().inject(this)
        val str = shared.getStringValue("json", "")
        if (str != "") {
            var day = getNormalDay(str)
            view.setViewVisibility(R.id.list_view, View.VISIBLE)
            view.setViewVisibility(R.id.error_message, View.GONE)
            view.setTextViewText(R.id.day_and_date, day.dayName)
            setList(view, c, appWI[0], day)
        } else {
            view.setViewVisibility(R.id.list_view, View.GONE)
            view.setViewVisibility(R.id.error_message, View.VISIBLE)
            view.setTextViewText(R.id.day_and_date, "")
        }
        setClickListeners()
        appWM.updateAppWidget(appWI[0], view)
    }

    private fun getNormalDay(str: String): Day {
        var number = dayInfo.scrollTo()
        val weekNumber = dayInfo.getWeekNumber()
        var week = parser.parseFromSharedPreference(str)[weekNumber - 1]

        while (number != 8) {
            if (week.days[number].lessons?.isNotEmpty()!!) return week.days[number]
            number++
        }
        week = parser.parseFromSharedPreference(str)[anotherWeek(number) - 1]
        number = 0
        while (number != 7) {
            if (week.days[number].lessons?.isNotEmpty()!!) return week.days[number]
            number++
        }
        return week.days[0]
    }

    private fun anotherWeek(week: Int): Int = if (week == 1) 2 else 1

    private fun setList(rv: RemoteViews, context: Context, appWidgetId: Int, day: Day) {
        val adapter = Intent(context, MyService::class.java)
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        adapter.putExtra("day", day.toString())
        rv.setRemoteAdapter(R.id.list_view, adapter)
    }


    private fun setClickListeners() {
        /*    view.setOnClickPendingIntent(R.id.frame_layout,
                    getPendingSelfIntent(c, MyOnClick))*/
        val intent = Intent(c, ScheduleActivity::class.java)
        view.setOnClickPendingIntent(R.id.container, PendingIntent.getActivity(c, 1, intent, 0))
        /*  val intentSync = Intent(c, this.javaClass)
          intentSync.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE//You need to specify the action for the intent. Right now that intent is doing nothing for there is no action to be broadcasted.
          val pendingSync = PendingIntent . getBroadcast (c, 0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT); //You need to specify a proper flag for the intent. Or else the intent will become deleted.
          view.setOnClickPendingIntent(R.id.frame_layout, pendingSync);*/
    }

}
