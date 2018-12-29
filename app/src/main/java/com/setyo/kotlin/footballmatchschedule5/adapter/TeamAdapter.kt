package com.setyo.kotlin.footballmatchschedule5.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_team_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamAdapter(private val context: Context,
                  private val teams: List<TeamsItem>, private val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_team_item, p0, false))

    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, pos: Int) {
        holder.bindItem(teams[pos], listener)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(teams: TeamsItem, listener: (TeamsItem) -> Unit){

        itemView.tv_team.text = teams.strTeam

        Picasso.with(itemView.context).load(teams.strTeamBadge)
                .into(itemView.img_team)


        itemView.onClick { listener(teams) }
    }

}