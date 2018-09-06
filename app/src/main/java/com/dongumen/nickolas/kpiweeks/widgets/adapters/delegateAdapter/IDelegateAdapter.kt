package com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface IDelegateAdapter<VH : RecyclerView.ViewHolder, T> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: VH,
                         items: List<T>,
                         position: Int)

    fun onRecycled(holder: VH)

    fun isForViewType(item: T): Boolean
}
