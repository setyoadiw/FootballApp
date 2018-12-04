package com.setyo.kotlin.footballmatchschedule5.fragment.player


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson

import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.activity.DetailProfileActivity
import com.setyo.kotlin.footballmatchschedule5.activity.DetailTeamActivity
import com.setyo.kotlin.footballmatchschedule5.adapter.PlayerAdapter
import com.setyo.kotlin.footballmatchschedule5.model.Player.PlayerItem
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.presenter.PlayerPresenterImp
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import com.setyo.kotlin.footballmatchschedule5.view.PlayerView
import org.jetbrains.anko.find

class FragmentPlayersList : Fragment() , PlayerView{

    lateinit var presenter : PlayerPresenterImp
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayerAdapter
    private lateinit var recyclerView: RecyclerView
    private var data: MutableList<PlayerItem> = mutableListOf()

    val api = ApiService()
    val gson = Gson()
    var context: CoroutineContextProvider? = CoroutineContextProvider()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragView = inflater.inflate(R.layout.fragment_players_list, container, false)

        initPresenter()
        initView(fragView)


        return fragView
    }

    private fun initView(fragView: View) {

        recyclerView = fragView.find(R.id.recyclerviewplayer)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PlayerAdapter(requireContext(), data, { partItem: PlayerItem -> partItemClicked(partItem) })
        recyclerView.adapter = adapter

        progressBar = fragView.find(R.id.progressbarplayer)

        var teamid = arguments?.getString("teamid").toString()


        presenter.getDataPlayer(teamid)

    }

    private fun partItemClicked(partItem: PlayerItem) {
        val intent = Intent(requireContext(), DetailProfileActivity::class.java)
        intent.putExtra("dataParcelProfile",partItem)
        startActivity(intent)
    }

    private fun initPresenter() {
        presenter = PlayerPresenterImp(this,gson,api, this!!.context!!)

    }

    override fun berhasil(data: List<PlayerItem>) {

        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()

    }

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE


    }

    override fun hideLoading() {

        progressBar.visibility = View.GONE

    }


    companion object {
        fun newInstance(teamId: String): FragmentPlayersList {
            val fragment = FragmentPlayersList()
            val args = Bundle()
            args.putString("teamid",teamId)
            fragment.arguments = args
            return fragment
        }
    }


}
