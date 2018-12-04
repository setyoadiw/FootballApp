package com.setyo.kotlin.footballmatchschedule5.fragment.team

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setyo.kotlin.footballmatchschedule5.fragment.player.FragmentPlayersList

class MyTeamPagerAdapter(fm: FragmentManager,var textOverview : String , var teamId : String) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {

        return when (p0) {
            0 -> {
                FragmentOverview.newInstance(textOverview)
            }
            else -> {
                FragmentPlayersList.newInstance(teamId)
            }
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Overview"
            else -> {
                return "Players"
            }
        }
    }
}