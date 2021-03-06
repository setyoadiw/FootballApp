package com.setyo.kotlin.footballmatchschedule5.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.setyo.kotlin.footballmatchschedule5.fragment.event.FragmentNextEvent
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.fragment.favorite.FragmentFavoriteEvent
import com.setyo.kotlin.footballmatchschedule5.fragment.event.TabEventFragment
import com.setyo.kotlin.footballmatchschedule5.fragment.favorite.TabFavoriteFragment
import com.setyo.kotlin.footballmatchschedule5.fragment.team.FragmentTeam

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    var content: FrameLayout? = null


    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.last -> {
                val fragment = TabEventFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.next -> {
                val fragment = FragmentTeam.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.fav -> {
                val fragment = TabFavoriteFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        initView()
    }

    private fun initView() {
        //bottomnav
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = TabEventFragment.newInstance()
        addFragment(fragment)



    }

}
