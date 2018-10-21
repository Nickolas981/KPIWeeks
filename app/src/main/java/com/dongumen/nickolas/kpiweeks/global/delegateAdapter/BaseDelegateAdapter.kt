package com.dongumen.nickolas.kpiweeks.global.delegateAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDelegateAdapter<VH : BaseViewHolder, T : Any> : IDelegateAdapter<VH, T> {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun onBindViewHolder(
            view: View, item: T, viewHolder: VH)

    protected abstract fun createViewHolder(parent: View): VH

    override fun onRecycled(holder: VH) {}

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflatedView = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)
        val holder = createViewHolder(inflatedView)
        holder.setListener(object : BaseViewHolder.ItemInflateListener {
            override fun inflated(viewType: Any, view: View) {
                onBindViewHolder(view, viewType as T, holder)
            }
        })
        return holder
    }

    override fun onBindViewHolder(holder: VH, items: List<T>, position: Int) {
        holder.bind(items[position])
    }
}
