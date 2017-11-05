package com.dongumen.nickolas.kpiweeks.widgets.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.model.enteties.Lesson

/**
 * Created by Nickolas on 04.11.2017.
 */
open class ListAdapter : BaseAdapter() {


    var day = Day()
        set(value) {
            day = value
            notifyDataSetChanged()
        }


    override fun getItem(position: Int): Lesson? = day.lessons!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = day.lessons?.size!!


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}