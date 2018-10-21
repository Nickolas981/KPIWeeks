package com.dongumen.nickolas.kpiweeks.global.extentions

import info.hoang8f.android.segmented.SegmentedGroup

fun SegmentedGroup.select(position: Int) {
    if (position >= childCount) return
    getChildAt(position).performClick()
}

fun SegmentedGroup.setOnItemClicked(func: (Int) -> Unit) {
    for (i in 0..childCount) {
        getChildAt(i)?.run {
            onClick { func(i) }
        }
    }
}