package com.example.nickolas.kpiweeks.utils

import okhttp3.ResponseBody
import org.json.JSONObject


class ResponseToGroupList {
    companion object {
        fun parse(response: ResponseBody): Map<String, String> {
            val arr: MutableMap<String, String> = HashMap()
            val res = response.string()

            val r = JSONObject(res).getJSONArray("results")

//            val parser = JsonParser()
//            val r = parser.parse(res).asJsonObject.getAsJsonArray("result")

            for (i in 0 until r.length()) {
                val rr = r.getJSONObject(i)
                arr[rr.getString("name")] = rr.getString("id")
            }

//        (0 until r.length()).mapTo(arr)
//        { r.getJSONObject(it).getString("name") }

        return arr
    }
}
}