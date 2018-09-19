package com.dongumen.nickolas.kpiweeks.global.extentions

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


inline fun <reified T> String.parse(): T = GsonBuilder().create().fromJson<T>(this, T::class.java)
inline fun <reified T> String.parseToList(): T = GsonBuilder().create().fromJson(this, object : TypeToken<T>() {}.type)

