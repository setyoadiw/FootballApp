package com.setyo.kotlin.footballmatchschedule5.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.database.FavoriteTeam
import com.setyo.kotlin.footballmatchschedule5.database.database
import com.setyo.kotlin.footballmatchschedule5.fragment.team.MyTeamPagerAdapter
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.find
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailTeamActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private var isFavorite: Boolean = false


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

        initView()
        getDataAndInitiate()
        castToView()


        fab.setOnClickListener { view ->

            if (isFavorite) removeFromFavorite() else addToFavorite()
            isFavorite = !isFavorite
            setFavorite()
        }
        favoriteState()
        setFavorite()

    }


    private fun removeFromFavorite() {

        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITES_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam)
            }

            Snackbar.make(app_bar, "This Team removed to Favorited list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        } catch (e: SQLiteConstraintException){

            Snackbar.make(app_bar, e.localizedMessage, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITES_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            Log.d("favoriteTeamState", favorite.toString())
            if (!favorite.isEmpty()) isFavorite = true
        }

    }

    private fun setFavorite() {

        if (isFavorite){
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_favorite))
        }

        else{
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_unfavorite))
        }

    }


    private fun getDataAndInitiate() {

        val dataParcel = intent.extras.getParcelable<TeamsItem>("dataParcelTeam")
        val dataParcelFav = intent.extras.getParcelable<FavoriteTeam>("dataParcelFav")

        if(dataParcel != null){

            idTeam = dataParcel.idTeam.toString()
            nameTeam = dataParcel.strTeam.toString()
            yearTeam = dataParcel.intFormedYear.toString()
            stadiumTeam = dataParcel.strStadium.toString()
            imgTeam = dataParcel.strTeamBadge.toString()
            imgHeader = dataParcel.strStadiumThumb.toString()
            teamOverview = dataParcel.strDescriptionEN.toString()



        }else if(dataParcelFav != null){

            idTeam = dataParcelFav.teamId.toString()
            nameTeam = dataParcelFav.teamName.toString()
            yearTeam = dataParcelFav.teamYear.toString()
            stadiumTeam = dataParcelFav.teamStadium.toString()
            imgTeam = dataParcelFav.teamBadge.toString()
            imgHeader = dataParcelFav.teamImgHeader.toString()
            teamOverview = dataParcelFav.teamOverview.toString()

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

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITES_TEAM,
                        FavoriteTeam.TEAM_ID to idTeam,
                        FavoriteTeam.TEAM_NAME to nameTeam,
                        FavoriteTeam.TEAM_YEAR to yearTeam,
                        FavoriteTeam.TEAM_STADIUM to stadiumTeam,
                        FavoriteTeam.TEAM_OVERVIEW to teamOverview,
                        FavoriteTeam.TEAM_HEADER to imgHeader,
                        FavoriteTeam.TEAM_BADGE to imgTeam
                )
            }

            Snackbar.make(app_bar, "This Team saved to Favorited list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        } catch (e: SQLiteConstraintException){

        }

    }


}
