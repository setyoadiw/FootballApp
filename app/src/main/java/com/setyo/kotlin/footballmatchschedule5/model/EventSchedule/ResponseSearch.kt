package com.setyo.kotlin.footballmatchschedule5.model.EventSchedule


import com.google.gson.annotations.SerializedName


data class ResponseSearch(

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
)