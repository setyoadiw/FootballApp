package com.setyo.kotlin.footballmatchschedule5.fragment.event


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.setyo.kotlin.footballmatchschedule5.adapter.FootballAdapter
import com.setyo.kotlin.footballmatchschedule5.R
import com.setyo.kotlin.footballmatchschedule5.activity.DetailMatchActivity
import com.setyo.kotlin.footballmatchschedule5.view.MainView
import com.setyo.kotlin.footballmatchschedule5.model.EventSchedule.EventItem
import com.setyo.kotlin.footballmatchschedule5.network.ApiService
import com.setyo.kotlin.footballmatchschedule5.presenter.FootballPresenterImp
import com.setyo.kotlin.footballmatchschedule5.util.CoroutineContextProvider
import org.jetbrains.anko.find

class FragmentLastEvent : Fragment() , MainView {

    lateinit var presenter : FootballPresenterImp
    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var search: SearchView
    private lateinit var adapter: FootballAdapter
    private lateinit var recyclerView: RecyclerView
    private var data: MutableList<EventItem> = mutableListOf()

    var currentPos: Int = 0
    var leagueId = emptyArray<String>()
    val api = ApiService()
    val gson = Gson()
    var context: CoroutineContextProvider? = CoroutineContextProvider()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentView : View = inflater.inflate(R.layout.fragment_last_event, container, false)
        setHasOptionsMenu(true)


        initPresenter()
        initView(fragmentView)

        return fragmentView
    }

    private fun initPresenter() {
        presenter = FootballPresenterImp(this,gson,api, this!!.context!!)

    }

    private fun initView(fragmentView: View) {
        
        //recyclerView
        recyclerView = fragmentView.find(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FootballAdapter(requireContext(), data, { partItem: EventItem -> partItemClicked(partItem) })
        recyclerView.adapter = adapter

        spinner = fragmentView.find(R.id.spinnerlast)
        progressBar = fragmentView.find(R.id.progressbar)
        val leagueName = resources.getStringArray(R.array.league_name)

        leagueId = resources.getStringArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagueName)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getDataLastEvents(leagueId[position])


                currentPos = position

            }
        }




    }

    private fun partItemClicked(partItem : EventItem) {

        val intent = Intent(requireContext(), DetailMatchActivity::class.java)
        intent.putExtra("dataParcel",partItem)
        startActivity(intent)

    }

    override fun berhasil(data: List<EventItem>) {

        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("CekDataBerhasil" , this.data.toString())


    }

    override fun berhasilNextEvent(data: List<EventItem>) {

    }

    override fun gagal(pesan: String) {

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
        search.queryHint = "Search Match"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)){
                    presenter.getDataLastEvents(leagueId[currentPos])
                }else{
                    presenter.getEventSearch(newText?.replace(" ", "_"))

                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }


    companion object {
        fun newInstance(): FragmentLastEvent {
            val fragment = FragmentLastEvent()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }



}
