package com.setyo.kotlin.footballmatchschedule5.presenter

import android.util.Log
import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.view.MainView
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.EventItem
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.ResponseEvents
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.ResponseSearch
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.network.Network
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FootballPresenterImp(val MainView: MainView,
                           var gson: Gson,
                           var apiService: ApiService,
                           var context: CoroutineContextProvider) : FootballPresenter {

    init {
        this.context = CoroutineContextProvider()
    }


    override fun getDataLastEvents(event: String?) {

        MainView.showLoading()

        Log.d("masukpresenterlast" , "ok")

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiService
                    .doRequest(Network.getLastSchedule(event)).await(),
                    ResponseEvents::class.java
            )
            MainView.berhasil(data.events as List<EventItem>)
            MainView.hideLoading()

        }


    }

    override fun getDataNextEvents(event: String?) {

        MainView.showLoading()

        Log.d("masukpresenternext" , "ok")


        GlobalScope.launch(context.main){
            val data2 = gson.fromJson(apiService
                    .doRequest(Network.getNextSchedule(event)).await(),
                    ResponseEvents::class.java
            )

            MainView.berhasilNextEvent(data2.events as List<EventItem>)
            MainView.hideLoading()

        }

    }

    fun getEventSearch(event: String?) {



        GlobalScope.launch(Dispatchers.Main){

            try{

                val data3 = gson.fromJson(apiService
                        .doRequest(Network.getScheduleBySearch(event)).await(),
                        ResponseSearch::class.java)

                if(data3 != null){
                    MainView.berhasil(data3.event as List<EventItem>)
                    MainView.berhasilNextEvent(data3.event)
                }

                Log.d("mengecekdatasearchevent",data3.toString())

            }catch (e: TypeCastException){
                e.printStackTrace()
            }

        }

    }


}