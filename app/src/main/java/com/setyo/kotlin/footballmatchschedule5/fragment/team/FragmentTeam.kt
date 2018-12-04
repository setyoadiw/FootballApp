package com.setyo.kotlin.footballmatchschedule5.fragment.team


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.activity.DetailTeamActivity
import com.setyo.kotlin.footballmatchschedule5.adapter.TeamAdapter
import com.setyo.kotlin.footballmatchschedule5.model.TeamDetail.TeamsItem
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.presenter.TeamPresenterImp
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import com.setyo.kotlin.footballmatchschedule5.view.TeamView
import org.jetbrains.anko.find
import java.net.URLEncoder


class FragmentTeam : Fragment() ,TeamView{

    lateinit var presenter : TeamPresenterImp
    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var search: SearchView
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String
    private lateinit var recyclerView: RecyclerView
    private var data: MutableList<TeamsItem> = mutableListOf()


    val api = ApiService()
    val gson = Gson()
    var context: CoroutineContextProvider? = CoroutineContextProvider()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragView = inflater.inflate(R.layout.fragment_team, container, false)

        setHasOptionsMenu(true)

        initPresenter()
        initView(fragView)

        return fragView
    }

    private fun initView(fragView: View) {

        //recyclerView
        recyclerView = fragView.find(R.id.recyclerviewteam)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TeamAdapter(requireContext(), data, { partItem: TeamsItem -> partItemClicked(partItem) })
        recyclerView.adapter = adapter

        spinner = fragView.find(R.id.spinner_team)
        progressBar = fragView.find(R.id.progressbarteam)

        val items = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = URLEncoder.encode(spinner.selectedItem.toString(), "UTF-8")
                Log.d("dataspinner",leagueName)
                presenter.getDataTeam(leagueName)



            }
        }

    }

    private fun initPresenter() {

        presenter = TeamPresenterImp(this,gson,api, this!!.context!!)

    }


    override fun berhasil(data: List<TeamsItem>) {

        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()

    }

    private fun partItemClicked(partItem: TeamsItem) {
        val intent = Intent(requireContext(), DetailTeamActivity::class.java)
        intent.putExtra("dataParcelTeam",partItem)
        startActivity(intent)

    }

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE

    }

    override fun hideLoading() {

        progressBar.visibility = View.GONE

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)

        search = searchItem?.actionView as SearchView
        search.queryHint = "Search Team"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)){
                    presenter.getDataTeam(leagueName)
                }else{

                    presenter.getTeamSearch(newText?.replace(" ", "_"))

                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }

    companion object {
        fun newInstance(): FragmentTeam {
            val fragment = FragmentTeam()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}
