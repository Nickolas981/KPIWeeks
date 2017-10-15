package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Group(@SerializedName("okr") val okr: Int? = null,
                 @SerializedName("name") val name: String? = null,
                 @SerializedName("id") val id: Int? = null,
                 @SerializedName("type") val type: Int? = null
)