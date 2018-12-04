package com.setyo.kotlin.footballmatchschedule5.model.Player

import com.google.gson.annotations.SerializedName

data class ResponsePlayer(

	@field:SerializedName("player")
	val player: List<PlayerItem?>? = null
)