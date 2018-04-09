package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.rafaelgarciafernandez.countrieskotlin.MyApplication
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerCountryListViewComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.CountryListViewModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CountryListActivity : AppCompatActivity(), CountryListMvp.View {

    @Inject
    lateinit var presenter: CountryListPresenter

    private var countryList: MutableList<CountryListViewModel> = mutableListOf()
    private lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
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
        val applicationComponent = (application as MyApplication).getApplicationComponent()
        DaggerCountryListViewComponent.builder()
                .applicationComponent(applicationComponent)
                .countryListViewModule(CountryListViewModule(this))
                .build().inject(this)

        presenter.init()
    }

    override fun showError() {
        Snackbar.make(findViewById<View>(android.R.id.content), R.string.there_was_an_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, { presenter.retry() })
                .show()
    }

    override fun updateList(countries: List<CountryListViewModel>) {
        countryList.clear()
        countryList.addAll(countries)
        adapter.notifyDataSetChanged()
    }
}
