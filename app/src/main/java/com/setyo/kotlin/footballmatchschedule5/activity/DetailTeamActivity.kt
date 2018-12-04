package com.setyo.kotlin.footballmatchschedule5.activity

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.fragment.team.FragmentOverview
import com.setyo.kotlin.footballmatchschedule5.fragment.player.FragmentPlayersList
import com.setyo.kotlin.footballmatchschedule5.fragment.team.MyTeamPagerAdapter
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.find

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private var nameTeam : String = ""
    private var idTeam : String = ""
    private  var yearTeam : String = ""
    private  var stadiumTeam : String = ""
    private  var imgTeam : String = ""
    private  var teamOverview : String = ""
    private  var imgHeader : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
//        setSupportActionBar(toolbar)

        initView()
        getDataAndInitiate()
        castToView()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }



    private fun getDataAndInitiate() {

        val dataParcel = intent.extras.getParcelable<TeamsItem>("dataParcelTeam")

        if(dataParcel != null){


            idTeam = dataParcel.idTeam.toString()
            nameTeam = dataParcel.strTeam.toString()
            yearTeam = dataParcel.intFormedYear.toString()
            stadiumTeam = dataParcel.strStadium.toString()
            imgTeam = dataParcel.strTeamBadge.toString()
            imgHeader = dataParcel.strStadiumThumb.toString()
            teamOverview = dataParcel.strDescriptionEN.toString()



        }
    }

    private fun castToView() {

        tv_name_team.text = nameTeam
        tv_stadium.text = stadiumTeam
        tv_year.text = yearTeam

        Picasso.with(this).load(imgTeam).into(img_team)
        Picasso.with(this).load(imgHeader).into(img_header_team)

        val fragmentAdapter = MyTeamPagerAdapter(supportFragmentManager,teamOverview,idTeam)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

    }

    private fun initView() {

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbar.isTitleEnabled = false

        viewPager = find(R.id.viewpager_team)
        tabs = find(R.id.tabs_team)
    }


}
