package com.dongumen.nickolas.kpiweeks.widgets.adapters.week

import android.view.LayoutInflater
import android.view.View
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.model.enteties.Item
import com.dongumen.nickolas.kpiweeks.model.enteties.Lesson
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil
import com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter.DelegateAdapter
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.lesson_view.view.*
import kotlinx.android.synthetic.main.lessons_list_view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
//Todo : Create adapter for each lesson
class WeekLessonAdapter(private val onDeleteClicked: (Lesson) -> Unit) : DelegateAdapter<Item>(), KoinComponent {

    private val dayInformationUtil by inject<DayInformationUtil>()

    override fun onBind(item: Item, viewHolder: KViewHolder) {
        with(viewHolder) {
            val day = item as Day
            val layoutInflater = LayoutInflater.from(viewHolder.itemView.context)
            day.lessons?.forEach {
                val lesson = it!!
                val v = layoutInflater.inflate(R.layout.lesson_view, null)
                v.number_of_lesson.text = it.lessonNumber
                if (it.lessonId == day.lessons.last()?.lessonId) {
                    v.separator.visibility = View.GONE
                }
                v.subject_name.text = it.lessonName
                v.teacher_name.text = it.teacherName
                v.room_number.text = "Аудиторія: ${it.lessonRoom}  (${it.lessonType})"
                val date = day.dayName?.replace("[^0-9.]".toRegex(), "")
                if (dayInformationUtil.isActive(it.lessonNumber.toString(), date!!)) {
                    v.indicator.visibility = View.VISIBLE
                }
                v.lesson_start.text = it.timeStart?.substring(0, 5)
                v.lesson_end.text = it.timeEnd?.substring(0, 5)
                v.delete.onClick {
                    onDeleteClicked.invoke(lesson)
                }
                container.addView(v)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.lessons_list_view
    }

    override fun isForViewType(item: Item?): Boolean {
        return item is Day
    }

}