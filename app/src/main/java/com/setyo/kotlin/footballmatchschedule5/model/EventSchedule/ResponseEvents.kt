package com.setyo.kotlin.footballmatchschedule5.model.EventSchedule


import com.google.gson.annotations.SerializedName


data class ResponseEvents(

	@field:SerializedName("events")
	val events: List<EventItem?>? = null
)