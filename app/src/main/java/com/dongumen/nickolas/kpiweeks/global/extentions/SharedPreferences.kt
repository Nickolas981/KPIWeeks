package com.dongumen.nickolas.kpiweeks.global.extentions

import android.content.SharedPreferences

fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    this.edit().apply { func() }.apply()
}