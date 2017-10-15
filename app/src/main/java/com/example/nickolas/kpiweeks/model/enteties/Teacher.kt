package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Teacher(@SerializedName("full_name") val fullName: String? = null,
                   @SerializedName("short_name_with_degree")
                   val shortNameWithDegree: String? = null,
                   @SerializedName("name") val name: String? = null,
                   @SerializedName("degree") val degree: String? = null,
                   @SerializedName("last_name") val lastName: String? = null,
                   @SerializedName("short_name") val shortName: String? = null,
                   @SerializedName("id") val id: Int? = null,
                   @SerializedName("middle_name") val middleName: String? = null,
                   @SerializedName("first_name") val firstName: String? = null
)