package com.example.nickolas.kpiweeks.widgets.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.example.nickolas.kpiweeks.R
import com.example.nickolas.kpiweeks.model.enteties.Day
import com.example.nickolas.kpiweeks.model.enteties.HeaderItem
import com.example.nickolas.kpiweeks.model.enteties.Item
import com.example.nickolas.kpiweeks.model.enteties.Week
import com.example.nickolas.kpiweeks.utils.DayInformationUtil
import com.example.nickolas.kpiweeks.utils.SimpleDiffCallback
import kotlinx.android.synthetic.main.day_view.view.*
import kotlinx.android.synthetic.main.lesson_view.view.*
import kotlinx.android.synthetic.main.lessons_list_view.view.*


open class WeekAdapter(private val context: Context) : RecyclerView.Adapter<WeekAdapter.BaseViewHolder>(), StickyHeaderHandler {


    protected var week = Week()
    private var dayKeys = week.days.keys
    private lateinit var dates: List<String>
    private var list: MutableList<Item> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.N)
    fun setWeek(w: Week, number: Int) {
        val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(list, w.getList()))
        week = w
        dayKeys = week.days.keys
        dates = DayInformationUtil().getDates(number)
        list = week.getList()
        diffResult.dispatchUpdatesTo(this)
    }


    fun getKeys(): MutableSet<String> = dayKeys
    override fun getAdapterData(): MutableList<*> = list


    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        val item = list[position]
        val dayInformationUtil = DayInformationUtil()
        if (getItemViewType(position) == 1) {
            item as HeaderItem
            val header = holder as HeaderHolder
            header.header.text = dayInformationUtil.getDay(item.text) + " " +
                    dates[item.text.toInt() - 1]
        } else {
            val day = item as Day
            val hold = holder as LessonHolder
            val lessonKey = day.lesssons.keys
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            hold.container.removeAllViews()
            for (key in lessonKey) {
                with(hold) {
                    val lesson = day.lesssons[key]
                    val v = vi.inflate(R.layout.lesson_view, null)
                    v.number_of_lesson.text = key
                    if (key == lessonKey.elementAt(lessonKey.size - 1)) {
                        v.separator.visibility = View.GONE
                    }
                    v.subject_name.text = lesson?.discipline?.fullName

                    if (lesson?.teachers?.isNotEmpty()!!)
                        v.teacher_name.text = lesson.teachers[0]?.name
                    if (lesson.rooms?.isNotEmpty()!!)
                        v.room_number.text = "Аудиторія: ${lesson.rooms[0]?.name} " +
                                "(${lesson.rooms[0]?.building?.name})"
                    if (dayInformationUtil.isActive(key, dates[day.day.toInt() - 1] )){
                        v.indicator.visibility = View.VISIBLE
                    }
                    v.lesson_start.text = dayInformationUtil.getStartTime(key)
                    v.lesson_end.text = dayInformationUtil.getFinishTime(key)
                    container.addView(v)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int = if (position % 2 == 0) 1 else 2


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(
                        if (viewType == 1)
                            R.layout.day_view
                        else
                            R.layout.lessons_list_view
                        , parent, false)

        return (if (viewType == 1)
            HeaderHolder(view)
        else
            LessonHolder(view))
    }


    override fun getItemCount(): Int = list.size


    inner class HeaderHolder(itemView: View) : BaseViewHolder(itemView) {
        var header: TextView = itemView.day_and_date
    }

    inner class LessonHolder(itemView: View) : BaseViewHolder(itemView) {
        var container = itemView.container!!
    }


    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}