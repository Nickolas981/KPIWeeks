package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class RoomsItem(

	@field:SerializedName("room_id")
	val roomId: String? = null,

	@field:SerializedName("room_longitude")
	val roomLongitude: String? = null,

	@field:SerializedName("room_name")
	val roomName: String? = null,

	@field:SerializedName("room_latitude")
	val roomLatitude: String? = null
)