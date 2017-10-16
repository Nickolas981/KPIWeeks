package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Discipline(

        @field:SerializedName("full_name")
        val fullName: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("id")
        val id: Int? = null
)