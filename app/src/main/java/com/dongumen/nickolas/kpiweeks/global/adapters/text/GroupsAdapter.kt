package com.dongumen.nickolas.kpiweeks.global.adapters.text

import com.dongumen.nickolas.kpiweeks.R
import com.dongumen.nickolas.kpiweeks.global.adapters.delegateAdapter.DelegateAdapter
import com.dongumen.nickolas.kpiweeks.global.extentions.onClick
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group
import kotlinx.android.synthetic.main.list_item_text.*

class GroupsAdapter(private val onClick: (Group) -> Unit) : DelegateAdapter<Group>() {
    override fun onBind(item: Group, viewHolder: KViewHolder) {
        with(viewHolder) {
            text.text = item.name
            viewHolder.itemView.onClick { onClick.invoke(item) }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.list_item_text
    }

    override fun isForViewType(item: Group?): Boolean {
        return true
    }
}