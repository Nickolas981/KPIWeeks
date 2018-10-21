package com.dongumen.nickolas.kpiweeks.global.delegateAdapter

import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class DelegateAdapter<T : Any>
    : BaseDelegateAdapter<DelegateAdapter.KViewHolder, T>() {

    abstract fun onBind(item: T, viewHolder: KViewHolder)

    final override fun onBindViewHolder(view: View, item: T, viewHolder: KViewHolder) {
        onBind(item, viewHolder)
    }

    override fun createViewHolder(parent: View): KViewHolder {
        return KViewHolder(parent)
    }

    class KViewHolder(override val containerView: View?)
        : BaseViewHolder(containerView!!), LayoutContainer
}
