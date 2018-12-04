package com.setyo.kotlin.footballmatchschedule5.activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.setyo.kotlin.footballmatchschedule5.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest{

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){
        //Aplikasi terbuka dan menampilkan beberapa view
        Thread.sleep(3000)

        //memeriksa viewpager tampil
        Espresso.onView(ViewMatchers.withId(viewpager_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        //melakukan swipe ke kiri
        Espresso.onView(ViewMatchers.withId(viewpager_main)).perform(ViewActions.swipeLeft())
        Thread.sleep(2000)

        //melakukan swipe ke kanan
        Espresso.onView(ViewMatchers.withId(viewpager_main)).perform(ViewActions.swipeRight())
        Thread.sleep(2000)

        //memeriksa spinner tampil
        Espresso.onView(ViewMatchers.withId(spinnerlast))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        //melakukan klik pada spinner
        Espresso.onView(ViewMatchers.withId(spinnerlast)).perform(ViewActions.click())
        Thread.sleep(2000)
        //melakukan klik pada item French Ligue 1
        Espresso.onView(ViewMatchers.withText("German Bundesliga")).perform(ViewActions.click())
        Thread.sleep(2000)

        //memastikan recyclerview last event tampil
        Espresso.onView(ViewMatchers.withId(recyclerview))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //melakukan scroll pada daftar ke-10
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerview)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(9))
        //melakukan klik pada daftar ke-2
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerview)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        //memeriksa tombol favorit tampil
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(fab))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //melakukan klik pada tombol floating action button favorit
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(fab)).perform(ViewActions.click())
        //memeriksa teks yang muncul pada spinner
        Espresso.onView(ViewMatchers.withText("This Match saved to Favorited list"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //kembali pada halaman sebelumnya
        Thread.sleep(3000)
        Espresso.pressBack()

        //memeriksa bottom navigation tampil
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //melakukan klik pada navigation favorite
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(fav)).perform(ViewActions.click())
        Thread.sleep(3000)

        //memastikan suatu daftar favorit tampil
        Espresso.onView(ViewMatchers.withId(recyclerviewFavorit))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //melakukan klik pada daftar ke-1
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(recyclerviewFavorit)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //memeriksa tombol favorit tampil
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(fab))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //melakukan klik pada tombol floating action button favorit
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(fab)).perform(ViewActions.click())
        //memeriksa teks yang muncul
        Espresso.onView(ViewMatchers.withText("This Match removed to Favorited list"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //kembali pada halaman sebelumnya
        Thread.sleep(3000)
        Espresso.pressBack()
    }



}