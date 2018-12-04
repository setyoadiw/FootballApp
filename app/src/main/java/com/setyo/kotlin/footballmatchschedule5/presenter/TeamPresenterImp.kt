package com.setyo.kotlin.footballmatchschedule5.presenter

import android.util.Log
import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.ResponseTeamDetail
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.network.Network
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import com.setyo.kotlin.footballmatchschedule5.view.TeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenterImp(val teamView: TeamView,
                       var gson: Gson,
                       var apiService: ApiService,
                       var context: CoroutineContextProvider) : TeamPresenter {

    init {
        this.context = CoroutineContextProvider()
    }

    override fun getDataTeam(team: String?) {
        teamView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiService
                    .doRequest(Network.getTeamList(team)).await(),
                    ResponseTeamDetail::class.java
            )
            Log.d("cekdatateamfromspinner",team.toString())
            Log.d("cekdatateam",data.teams.toString())

            teamView.berhasil(data.teams as List<TeamsItem>)
            teamView.hideLoading()

        }
    }

    override fun getTeamSearch(team: String?) {

        teamView.showLoading()

        GlobalScope.launch(context.main){

            try{

                val data2 = gson.fromJson(apiService
                        .doRequest(Network.getTeamSearch(team)).await(),
                        ResponseTeamDetail::class.java)

                teamView.berhasil(data2.teams as List<TeamsItem>)

                teamView.hideLoading()

            }catch (e : TypeCastException){
                e.printStackTrace()
            }

        }

    }

}