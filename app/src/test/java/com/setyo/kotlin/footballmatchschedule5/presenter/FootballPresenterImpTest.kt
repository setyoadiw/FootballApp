package com.setyo.kotlin.footballmatchschedule5.presenter

import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.EventItem
import com.setyo.kotlin.footballmatchschedule5.view.MainView
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.ResponseEvents
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.network.Network
import com.setyo.kotlin.footballmatchschedule5.util.TestCoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FootballPresenterImpTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var service: ApiService

    private lateinit var presenter: FootballPresenterImp

    @Mock
    private
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = FootballPresenterImp(view,gson,service, TestCoroutineContextProvider())

    }

    @Test
    fun getDataLastEvents(){

//        val event: ArrayList<EventItem> = arrayListOf()
//        val response : ResponseEvents = mock()
//        val response = mock(ResponseEvents::class.java
        val event: MutableList<EventItem> = mutableListOf()
        val response = ResponseEvents(event)
        val idLeague = "4328"


        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(service
                    .doRequest(Network.getLastSchedule(idLeague)).await(),
                    ResponseEvents::class.java
            )).thenReturn(response)


            presenter.getDataLastEvents(idLeague)
            Mockito.verify(view).berhasil(event)

        }



    }


}