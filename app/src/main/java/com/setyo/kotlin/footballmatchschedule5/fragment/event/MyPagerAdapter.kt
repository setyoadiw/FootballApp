package com.setyo.kotlin.footballmatchschedule5.fragment.event

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(pos: Int): Fragment {


        return when (pos) {
            0 -> {
                FragmentLastEvent.newInstance()
            }
            1 -> FragmentNextEvent.newInstance()

            else -> {
                return FragmentLastEvent.newInstance()
            }
        }

    }

    override fun getCount(): Int {
        return 2

    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Last Event"
            1 -> "Next Event"
            else -> {
                return "Search"
            }
        }
    }


}