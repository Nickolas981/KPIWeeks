package com.dongumen.nickolas.kpiweeks.global.extentions

import android.view.View
import android.view.View.*

fun View.onClick(func: (View) -> Unit) {
    setOnClickListener(func)
}

fun View.setCustomPadding(left: Int = paddingLeft,
                          top: Int = paddingTop,
                          right: Int = paddingRight,
                          bottom: Int = paddingBottom) {
    setPadding(left, top, right, bottom)
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}

fun View.invisible() {
    visibility = INVISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.setVisibility(visible: Boolean) = if (visible) show() else hide()