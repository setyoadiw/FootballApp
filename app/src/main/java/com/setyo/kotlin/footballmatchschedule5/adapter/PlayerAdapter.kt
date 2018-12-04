package com.setyo.kotlin.footballmatchschedule5.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.model.Player.PlayerItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_player_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(private val context: Context,private val player: List<PlayerItem>,
                    private val listener: (PlayerItem) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {

        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_player_item, p0, false))

    }

    override fun getItemCount(): Int = player.size


    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItem(player[p1], listener)
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(playerItem: PlayerItem, listener: (PlayerItem) -> Unit) {

        itemView.tv_player.text = playerItem.strPlayer

        Picasso.with(itemView.context).load(playerItem.strCutout)
                .into(itemView.img_player)

        itemView.onClick { listener(playerItem) }


    }

}
