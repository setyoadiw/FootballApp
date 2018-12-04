package com.setyo.kotlin.footballmatchschedule5.fragment.event


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
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


class FragmentNextEvent : Fragment() , MainView {

    lateinit var presenter : FootballPresenterImp
    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var search: SearchView
    private lateinit var adapter: FootballAdapter
    private lateinit var recyclerView: RecyclerView
    private var data: MutableList<EventItem> = mutableListOf()

    var currentPosition: Int = 0
    var leagueId = emptyArray<String>()
    val api = ApiService()
    val gson = Gson()
    var context: CoroutineContextProvider? = CoroutineContextProvider()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragView = inflater.inflate(R.layout.fragment_next_event, container, false)

        setHasOptionsMenu(true)


        initPresenter()
        initView(fragView)

        return fragView
    }

    private fun initPresenter() {
        presenter = FootballPresenterImp(this,gson,api, this!!.context!!)
    }

    private fun initView(fragView: View) {

        //recyclerView
        recyclerView = fragView.find(R.id.recyclerview2)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FootballAdapter(requireContext(), data, { partItem: EventItem -> partItemClicked(partItem) })
        recyclerView.adapter = adapter

        spinner = fragView.find(R.id.spinner2)
        progressBar = fragView.find(R.id.progressbar2)
        val leagueName = resources.getStringArray(R.array.league_name)

        leagueId = resources.getStringArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagueName)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getDataNextEvents(leagueId[position])
                currentPosition = position

            }
        }


    }

    private fun partItemClicked(partItem : EventItem) {

        val intent = Intent(requireContext(), DetailMatchActivity::class.java)
        intent.putExtra("dataParcel",partItem)
        startActivity(intent)


    }


    override fun berhasil(data: List<EventItem>) {

    }

    override fun berhasilNextEvent(data: List<EventItem>) {

        this.data.clear()
        this.data.addAll(data)
        adapter.notifyDataSetChanged()

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
                    presenter.getDataNextEvents(leagueId[currentPosition])
                }else{

                    presenter.getEventSearch(newText?.replace(" ", "_"))
//                    presenter.getEventSearch("arsenal")

                }
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }

    companion object {
        fun newInstance(): FragmentNextEvent {
            val fragment = FragmentNextEvent()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


}
