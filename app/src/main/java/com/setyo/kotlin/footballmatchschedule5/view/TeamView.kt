package com.setyo.kotlin.footballmatchschedule5.view

import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem

interface TeamView {

    fun berhasil(data: List<TeamsItem>)
    fun showLoading()
    fun hideLoading()
}