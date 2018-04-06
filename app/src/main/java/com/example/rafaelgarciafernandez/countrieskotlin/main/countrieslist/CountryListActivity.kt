package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.rafaelgarciafernandez.countrieskotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class CountryListActivity : AppCompatActivity(), CountryListMvp.View {

    private var countryList: MutableList<CountryListViewModel> = mutableListOf()
    private lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        init()
    }

    private fun initViews() {
        setList()

    }

    private fun setList() {
        countriesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryListAdapter(countryList) {
            Toast.makeText(applicationContext, it.name, Toast.LENGTH_LONG).show()
        }
        countriesRecyclerView.adapter = adapter
        countriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            }
        })
    }

    private fun init() {
    }
}
