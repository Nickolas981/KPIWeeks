package com.dongumen.nickolas.kpiweeks.widgets.adapters.week

import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.model.enteties.HeaderItem
import com.dongumen.nickolas.kpiweeks.model.enteties.Item
import com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter.DelegateAdapter
import kotlinx.android.synthetic.main.day_view.*

class WeekHeaderAdapter : DelegateAdapter<Item>() {
    override fun onBind(item: Item, viewHolder: KViewHolder) {
        with(viewHolder) {
            day_and_date.text = (item as HeaderItem).text
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.day_view
    }

    override fun isForViewType(item: Item?): Boolean {
        return item is HeaderItem
    }

}