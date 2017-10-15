package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Building(@SerializedName("id") val id: Int? = null,
                    @SerializedName("name") var name: String? = null,
                    @SerializedName("latitude") var latitude: Double? = null,
                    @SerializedName("longitude") var longitude: Double? = null)