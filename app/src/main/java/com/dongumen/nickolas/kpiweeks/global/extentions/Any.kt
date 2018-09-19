package com.dongumen.nickolas.kpiweeks.global.extentions

import com.google.gson.GsonBuilder

fun Any.toGsonString(): String = GsonBuilder().create().toJson(this)