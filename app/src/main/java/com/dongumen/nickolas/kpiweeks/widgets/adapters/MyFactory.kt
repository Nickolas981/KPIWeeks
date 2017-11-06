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
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*


class MyFactory internal constructor() : RemoteViewsService.RemoteViewsFactory {


    /* internal lateinit var day: Day
     var data: ArrayList<String>? = null
     @SuppressLint("SimpleDateFormat")
     private var sdf: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
     private var widgetID: Int = 0

     init {
         widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                 AppWidgetManager.INVALID_APPWIDGET_ID)
     }


     override fun getLoadingView(): RemoteViews? {
         return null
     }

     override fun getCount(): Int = data!!.size
 //    override fun getCount(): Int = day.lessons!!.size

     override fun getViewTypeCount(): Int = 1

     override fun onCreate() {
         day = Day()
         data = ArrayList()
     }

     override fun getItemId(position: Int): Long {
         return position.toLong()
     }

     override fun getViewAt(position: Int): RemoteViews {
 //        val rView = RemoteViews(context.packageName,
 //                R.layout.lesson_view)
 //        rView.setTextViewText(R.id.number_of_lesson, (position + 1).toString())
 //        rView.setTextViewText(R.id.subject_name, day.lessons!![position]!!.lessonName)
 //        rView.setTextViewText(R.id.teacher_name, day.lessons!![position]!!.teacherName)
 //        rView.setTextViewText(R.id.room_number, day.lessons!![position]!!.lessonRoom)
 //        rView.setTextViewText(R.id.lesson_start, day.lessons!![position]!!.timeStart)
 //        rView.setTextViewText(R.id.lesson_end, day.lessons!![position]!!.timeEnd)
 //        return rView
         val rView = RemoteViews(context.packageName, R.layout.item)
         rView.setTextViewText(R.id.tvItemText, data?.get(position))
         return rView
     }

     override fun hasStableIds(): Boolean {
         return true
     }

     override fun onDataSetChanged() {
         data!!.clear()
         for (i in 3..14) {
             data!!.add("Item " + i)
         }
     }

     override fun onDestroy() {

     }
 */

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
        data.addAll(day.lessons)
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
