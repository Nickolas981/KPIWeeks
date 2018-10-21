package com.dongumen.nickolas.kpiweeks.global.delegateAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface IDelegateAdapter<VH : RecyclerView.ViewHolder, T> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: VH,
                         items: List<T>,
                         position: Int)

    fun onRecycled(holder: VH)

    fun isForViewType(item: T): Boolean
}
