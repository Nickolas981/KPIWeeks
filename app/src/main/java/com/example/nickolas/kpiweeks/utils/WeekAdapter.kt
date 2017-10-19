package com.example.nickolas.kpiweeks.utils

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nickolas.kpiweeks.R
import com.example.nickolas.kpiweeks.model.enteties.Week
import kotlinx.android.synthetic.main.day_view.view.*
import kotlinx.android.synthetic.main.lesson_view.view.*

class WeekAdapter(private val context: Context) : RecyclerView.Adapter<WeekAdapter.ViewHolder>(){

    private var week = Week()
    private var dayKeys = week.days.keys

    fun setWeek(w : Week){
        week = w
        dayKeys = week.days.keys
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder){
            val day = week.days[dayKeys.elementAt(position)]
            this!!.dateAndDay.text = getDay(dayKeys.elementAt(position))
            val lessonKey = week.days[dayKeys.elementAt(position)]?.lesssons?.keys
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            for (key in lessonKey!!){
                val lesson = day?.lesssons?.get(key)
                val v = vi.inflate(R.layout.lesson_view, null)
                v.number_of_lesson.text = key
                if(key == lessonKey.elementAt(lessonKey.size - 1)){
                    v.separator.visibility = View.GONE
                }
                v.subject_name.text = lesson?.discipline?.fullName

                if (lesson?.teachers?.isNotEmpty()!!)
                    v.teacher_name.text = lesson.teachers[0]?.name
                if (lesson.rooms?.isNotEmpty()!!) {
                    v.room_number.text = "Аудиторія: ${lesson.rooms[0]?.name} " +
                            "(${lesson.rooms.get(0)?.building?.name})"
                }
                v.lesson_start.text = getStart(key)
                v.lesson_end.text = getFinish(key)
                container.addView(v)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.day_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = week.days.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateAndDay :TextView = itemView.day_and_date
        var container = itemView.lessons_container
    }

    fun getDay(id : String) : String{
        return when(id){
            "1" -> "Понеділок"
            "2" -> "Вівторок"
            "3" -> "Середа"
            "4" -> "Четвер"
            "5" -> "П'ятниця"
            "6" -> "Субота"
            else -> "hz"
        }
    }

    fun getStart(id : String) : String{
        return when(id){
            "1" -> "8:30"
            "2" -> "10:25"
            "3" -> "12:20"
            "4" -> "14:15"
            "5" -> "16:10"
            "6" -> "18:30"
            "7" -> "20:20"
            else -> "hz"
        }
    }
    fun getFinish(id : String) : String{
        return when(id){
            "1" -> "10:05"
            "2" -> "12:00"
            "3" -> "13:55"
            "4" -> "15:50"
            "5" -> "17:45"
            "6" -> "20:05"
            "7" -> "21:55"
            else -> "hz"
        }
    }
}