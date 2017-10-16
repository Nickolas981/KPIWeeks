package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class TeachersItem(

        @field:SerializedName("full_name")
        val fullName: String? = null,

        @field:SerializedName("short_name_with_degree")
        val shortNameWithDegree: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("degree")
        val degree: String? = null,

        @field:SerializedName("last_name")
        val lastName: String? = null,

        @field:SerializedName("short_name")
        val shortName: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("middle_name")
        val middleName: String? = null,

        @field:SerializedName("first_name")
        val firstName: String? = null
)