package com.setyo.kotlin.footballmatchschedule5.activity

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.model.Player.PlayerItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_profile.*
import kotlinx.android.synthetic.main.content_detail_profile.*

class DetailProfileActivity : AppCompatActivity() {

    private var isFavorite: Boolean = false
    lateinit var nameProfile : String
    lateinit var imgProfile : String
    lateinit var weightProfile : String
    lateinit var heightProfile : String
    lateinit var position : String
    lateinit var textDescription : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profile)
        setSupportActionBar(toolbar)

        getDataAndInitiate()
        initView()

    }

    private fun getDataAndInitiate() {


        val dataParcel = intent.extras.getParcelable<PlayerItem>("dataParcelProfile")


        if (dataParcel != null) {

            nameProfile = dataParcel.strPlayer.toString()
            weightProfile = dataParcel.strWeight.toString()
            heightProfile = dataParcel.strHeight.toString()
            position = dataParcel.strPosition.toString()
            textDescription = dataParcel.strDescriptionEN.toString()
            imgProfile = dataParcel.strFanart1.toString()

        }
    }

    private fun initView() {

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout_player)
        collapsingToolbar.title = nameProfile

        Picasso.with(this).load(imgProfile).into(img_header_profile)

        tv_weight.text = weightProfile
        tv_height.text = heightProfile
        tv_pos.text = position
        tv_description.text = textDescription



    }
}
