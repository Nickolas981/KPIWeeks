package com.example.nickolas.kpiweeks.utils

import okhttp3.ResponseBody
import org.json.JSONObject


class ResponseToGroupListUtil {
    companion object {
        fun parse(response: ResponseBody): MutableList<String> {
            val arr: MutableList<String> = ArrayList()
            val res = response.string()

            val r = JSONObject(res).getJSONArray("data")

            (0 until r.length())
                    .map { r.getJSONObject(it) }
                    .forEach { arr.add(it.getString("group_full_name")) }

            return arr
        }
    }
}