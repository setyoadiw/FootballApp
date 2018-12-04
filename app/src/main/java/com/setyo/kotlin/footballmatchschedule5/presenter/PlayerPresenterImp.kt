package com.setyo.kotlin.footballmatchschedule5.presenter

import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.model.Player.PlayerItem
import com.setyo.kotlin.footballmatchschedule5.model.Player.ResponsePlayer
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.network.Network
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import com.setyo.kotlin.footballmatchschedule5.view.PlayerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenterImp (val playerView: PlayerView,
                          var gson: Gson,
                          var apiService: ApiService,
                          var context: CoroutineContextProvider) : PlayerPresenter{

    init {
        this.context = CoroutineContextProvider()
    }


    override fun getDataPlayer(player: String?) {

        playerView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiService
                    .doRequest(Network.getPlayerList(player)).await(),
                    ResponsePlayer::class.java
            )

            playerView.berhasil(data.player as List<PlayerItem>)
            playerView.hideLoading()

        }

    }
}