package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Disciplines(@SerializedName("full_name") val fullName: String? = null,
                       @SerializedName("name") val name: String? = null,
                       @SerializedName("id") val id: Int? = null
)