package com.example.nickolas.kpiweeks.utils

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import org.json.JSONObject


class ResponseToGroupList {
    companion object {
        fun parse(response: ResponseBody): List<String> {
            val arr: MutableList<String> = ArrayList()
            val res = response.string()

            val r = JSONObject(res).getJSONArray("results")


//            val parser = JsonParser()
//            val r = parser.parse(res).asJsonObject.getAsJsonArray("result")

            (0 until r.length()).mapTo(arr) { r.getJSONObject(it).getString("name") }

            return arr
        }
    }
}