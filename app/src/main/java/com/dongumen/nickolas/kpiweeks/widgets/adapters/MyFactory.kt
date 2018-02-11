package com.dongumen.nickolas.kpiweeks.widgets.adapters

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.model.enteties.Lesson
import com.dongumen.nickolas.kpiweeks.widgets.MyWidget
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*


class MyFactory internal constructor() : RemoteViewsService.RemoteViewsFactory {

    lateinit var context: Context
    lateinit var data: ArrayList<Lesson>
    private lateinit var sdf: SimpleDateFormat
    var widgetID: Int = 0
    lateinit var day: Day

    @SuppressLint("SimpleDateFormat")
    constructor(context: Context, intent: Intent) : this() {
        this.context = context
        day = GsonBuilder().create()
                .fromJson<Day>(intent.getStringExtra("day"), Day::class.java)
        sdf = SimpleDateFormat("HH:mm:ss")
        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID)
    }

    override fun onCreate() {
        data = ArrayList()
    }

    override fun getCount(): Int {
        return day.lessons?.size!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rView = RemoteViews(context.packageName,
                R.layout.lesson_view)

        rView.setTextViewText(R.id.number_of_lesson, (position + 1).toString())
        rView.setTextViewText(R.id.subject_name, data[position].lessonName)
        rView.setTextViewText(R.id.teacher_name, data[position].teacherName)
        rView.setTextViewText(R.id.room_number, data[position].lessonRoom)
        rView.setTextViewText(R.id.lesson_start, data[position].timeStart!!.substring(0, 5))
        rView.setTextViewText(R.id.lesson_end, data[position].timeEnd!!.substring(0, 5))

        return rView
    }

    override fun getViewTypeCount(): Int = 1

    override fun hasStableIds(): Boolean = true

    override fun onDataSetChanged() {
        data.clear()
        data.addAll(MyWidget.day.lessons)
    }

    override fun onDestroy() {

    }

}

private fun <E> ArrayList<E>.addAll(elements: List<E?>?) {
    for (element in elements!!) {
        if (element != null) {
            this.add(element)
        }
    }
}
