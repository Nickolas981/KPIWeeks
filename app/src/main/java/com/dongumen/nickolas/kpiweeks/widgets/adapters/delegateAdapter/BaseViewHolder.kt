package com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
    private var listener: ItemInflateListener? = null

    fun setListener(listener: ItemInflateListener) {
        this.listener = listener
    }

    fun bind(item: Any) {
        listener!!.inflated(item, itemView)
    }

    interface ItemInflateListener {
        fun inflated(viewType: Any, view: View)
    }
}
