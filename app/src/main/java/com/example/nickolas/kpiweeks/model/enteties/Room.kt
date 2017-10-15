package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Room(@SerializedName("full_name") val fullName: String? = null,
                @SerializedName("kpimaps_id") val kpimapsId: Any? = null,
                @SerializedName("name") val name: String? = null,
                @SerializedName("id") val id: Int? = null,
                @SerializedName("building") val building: Int? = null)

