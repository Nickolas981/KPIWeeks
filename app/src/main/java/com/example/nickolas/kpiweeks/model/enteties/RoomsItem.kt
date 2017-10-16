package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class RoomsItem(

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("building")
        val building: Building? = null
)