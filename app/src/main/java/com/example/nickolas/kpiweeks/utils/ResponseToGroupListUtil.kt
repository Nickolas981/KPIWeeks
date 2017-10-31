package com.example.nickolas.kpiweeks.utils

import okhttp3.ResponseBody
import org.json.JSONObject


class ResponseToGroupListUtil {
    companion object {
        fun parse(response: ResponseBody): Map<String, String> {
            val arr: MutableMap<String, String> = HashMap()
            val res = response.string()

            val r = JSONObject(res).getJSONArray("data")


            (0 until r.length())
                    .map { r.getJSONObject(it) }
                    .forEach { arr[it.getString("group_full_name")] = it.getString("group_id") }


        return arr
    }
}
}