package com.setyo.kotlin.footballmatchschedule5.util


import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TestCoroutineContextProvider : CoroutineContextProvider(){
//    override val main: CoroutineContext = Unconfined
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined

//    override val IO: CoroutineContext = Unconfined

}