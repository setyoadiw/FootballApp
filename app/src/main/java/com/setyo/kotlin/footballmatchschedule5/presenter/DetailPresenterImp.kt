package com.setyo.kotlin.footballmatchschedule5.presenter

import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.view.DetailView
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.ResponseTeamDetail
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.network.Network
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailPresenterImp : DetailPresenter {

    var view : DetailView? = null
    var gson: Gson? = null
    var context: CoroutineContextProvider? = null
    var apiService : ApiService? = null

    constructor(view : DetailView?, gson: Gson,
                apiService: ApiService, context: CoroutineContextProvider){
        this.view = view
        this.context = context
        this.context = CoroutineContextProvider()
        this.gson = gson
        this.apiService = apiService

    }



    override fun getTeamDetail(idHome: String?,idAway: String?) {


        GlobalScope.launch(context?.main!!){
            val data = gson?.fromJson(apiService?.doRequest(Network.getTeamDetail(idHome))?.await(),
                    ResponseTeamDetail::class.java
            )

            val data2 = gson?.fromJson(apiService?.doRequest(Network.getTeamDetail(idAway))?.await(),
                    ResponseTeamDetail::class.java
            )
            view?.getTeamHome(data?.teams as List<TeamsItem>)
            view?.getTeamAway(data2?.teams as List<TeamsItem>)

        }


    }



}