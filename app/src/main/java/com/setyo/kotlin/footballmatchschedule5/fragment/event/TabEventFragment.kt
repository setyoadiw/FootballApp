package com.setyo.kotlin.footballmatchschedule5.fragment.event


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setyo.kotlin.footballmatchschedule5.R
import org.jetbrains.anko.find


class TabEventFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    companion object {
        fun newInstance(): TabEventFragment{
            val fragment = TabEventFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var fragView = inflater.inflate(R.layout.fragment_tab_event, container, false)

        initView(fragView)

        return fragView
    }

    private fun initView(fragView: View) {

        viewPager = fragView.find(R.id.viewpager_main)
        tabs = fragView.find(R.id.tabs_main)

        val fragmentAdapter = MyPagerAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)
    }




}
