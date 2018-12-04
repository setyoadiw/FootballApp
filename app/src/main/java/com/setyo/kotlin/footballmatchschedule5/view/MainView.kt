package com.setyo.kotlin.footballmatchschedule5.view

import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.EventItem

interface MainView {

    fun berhasil(data: List<EventItem>)
    fun berhasilNextEvent(data: List<EventItem>)
    fun gagal(pesan : String)
    fun showLoading()
    fun hideLoading()

}