package com.example.nickolas.kpiweeks.widgets.adapters

import android.annotation.SuppressLint
import android.content.Context
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


    var week = Week()
        set(w) {
            val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(list, w.getAsList()))
            field = w
            list = week.getAsList()
            diffResult.dispatchUpdatesTo(this)
        }
    private var list: MutableList<Item> = ArrayList()


    override fun getAdapterData(): MutableList<*> = list


    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        val item = list[position]
        val dayInformationUtil = DayInformationUtil()
        if (getItemViewType(position) == 1) {
            item as HeaderItem
            val header = holder as HeaderHolder
            if ((list[position + 1] as Day).lessons?.isNotEmpty()!!) {
                header.header.text = item.text
                header.header.visibility = View.VISIBLE
            }
        } else if(getItemViewType(position) == 2){
            val day = item as Day
            val hold = holder as LessonHolder
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            hold.container.removeAllViews()
            for (lesson in day.lessons!!) {
                with(hold) {
                    val v = vi.inflate(R.layout.lesson_view, null)
                    v.number_of_lesson.text = lesson?.lessonNumber
                    if (lesson?.lessonId == day.lessons.last()?.lessonId) {
                        v.separator.visibility = View.GONE
                    }
                    v.subject_name.text = lesson?.lessonName
                    v.teacher_name.text = lesson?.teacherName
                    v.room_number.text = "Аудиторія: ${lesson?.lessonRoom}"
                    val date = day.dayName?.replace("[^0-9.]".toRegex(), "")
                    if (dayInformationUtil.isActive(lesson?.lessonNumber.toString(), date!!)) {
                        v.indicator.visibility = View.VISIBLE
                    }
                    v.lesson_start.text = lesson?.timeStart?.substring(5)
                    v.lesson_end.text = lesson?.timeEnd?.substring(5)
                    container.addView(v)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0){
            val day = list[position + 1] as Day
            if (day.lessons?.isEmpty()!!)
                return 3
            return 1
        }
        return 2

    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(
                        when (viewType) {
                            1 -> R.layout.day_view
                            2 -> R.layout.lessons_list_view
                            else -> R.layout.empty_view
                        }
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