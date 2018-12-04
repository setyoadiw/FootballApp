package com.setyo.kotlin.footballmatchschedule5.fragment.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.setyo.kotlin.footballmatchschedule5.R
import kotlinx.android.synthetic.main.fragment_overview.*
import org.jetbrains.anko.find


class FragmentOverview : Fragment() {

    private lateinit var tvOverview: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragView = inflater.inflate(R.layout.fragment_overview, container, false)

        initView(fragView)

        return fragView
    }

    private fun initView(fragView: View) {
        tvOverview =  fragView.find(R.id.tv_overview)
        var text = arguments?.getString("teamoverview").toString()

        tvOverview.text =  text
    }

    companion object {
        fun newInstance(text : String?): FragmentOverview {
            val fragment = FragmentOverview()
            val args = Bundle()
            args.putString("teamoverview",text)
            fragment.arguments = args
            return fragment
        }
    }


}
