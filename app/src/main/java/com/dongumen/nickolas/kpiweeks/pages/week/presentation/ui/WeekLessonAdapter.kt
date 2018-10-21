package com.dongumen.nickolas.kpiweeks.pages.week.presentation.ui

import android.view.View
import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.global.delegateAdapter.DelegateAdapter
import com.dongumen.nickolas.kpiweeks.global.extentions.onClick
import com.dongumen.nickolas.kpiweeks.global.utils.DayInformationUtil
import com.dongumen.nickolas.kpiweeks.model.enteties.Item
import com.dongumen.nickolas.kpiweeks.model.enteties.Lesson
import kotlinx.android.synthetic.main.lesson_view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class WeekLessonAdapter(private val onDeleteClicked: (Lesson) -> Unit) : DelegateAdapter<Item>(), KoinComponent {

    private val dayInformationUtil by inject<DayInformationUtil>()

    override fun onBind(item: Item, viewHolder: KViewHolder) {
        val lesson = item as Lesson
        with(viewHolder) {
            number_of_lesson.text = lesson.lessonNumber
            if (lesson.last) {
                separator.visibility = View.GONE
            }
            subject_name.text = lesson.lessonName
            teacher_name.text = lesson.teacherName
            room_number.text = "Аудиторія: ${lesson.lessonRoom}  (${lesson.lessonType})"
            val date = lesson.date
            if (dayInformationUtil.isActive(lesson.lessonNumber, date)) {
                indicator.visibility = View.VISIBLE
            }
            lesson_start.text = lesson.timeStart.substring(0, 5)
            lesson_end.text = lesson.timeEnd.substring(0, 5)
            delete.onClick {
                onDeleteClicked.invoke(lesson)
            }
        }
    }

    override val layoutId: Int = R.layout.lesson_view

    override fun isForViewType(item: Item): Boolean {
        return item is Lesson
    }

}