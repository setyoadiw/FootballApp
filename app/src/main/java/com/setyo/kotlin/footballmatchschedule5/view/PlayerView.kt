package com.setyo.kotlin.footballmatchschedule5.view

import com.setyo.kotlin.footballmatchschedule5.model.Player.PlayerItem

interface PlayerView {

    fun berhasil(data: List<PlayerItem>)
    fun showLoading()
    fun hideLoading()
}