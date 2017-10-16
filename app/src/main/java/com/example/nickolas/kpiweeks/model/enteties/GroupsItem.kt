package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class GroupsItem(

        @field:SerializedName("okr")
        val okr: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("type")
        val type: Int? = null
)