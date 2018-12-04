package com.setyo.kotlin.footballmatchschedule5.view

import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem

interface DetailView {

    fun getTeamHome(data: List<TeamsItem>)
    fun getTeamAway(data: List<TeamsItem>)


}