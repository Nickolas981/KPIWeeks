package com.dongumen.nickolas.kpiweeks.global.group

import android.content.SharedPreferences
import com.dongumen.nickolas.kpiweeks.global.extentions.edit
import com.dongumen.nickolas.kpiweeks.global.extentions.parse
import com.dongumen.nickolas.kpiweeks.global.extentions.toGsonString
import com.dongumen.nickolas.kpiweeks.pages.groupSearch.models.Group

class SharedPrefsGroupManager(private val sharedPreferences: SharedPreferences) : GroupManager {
    override var group: Group
        get() = sharedPreferences.getString("group", null)?.parse() ?: Group(-1, "empty")
        set(value) = sharedPreferences.edit { putString("group", value.toGsonString()) }
}