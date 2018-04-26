package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.rafaelgarciafernandez.countrieskotlin.MyApplication
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerCountryListViewComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.CountryListViewModule
import com.example.rafaelgarciafernandez.countrieskotlin.utils.setStatusBarColor
import com.example.rafaelgarciafernandez.countrieskotlin.utils.toast
import com.miguelcatalan.materialsearchview.MaterialSearchView
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    private fun initViews() {
        setToolbar()
        setSearchView()
        setList()
        setScrollToTopButton()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setSearchView() {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.onQueryTextChange(newText)
                return false
            }
        })

        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                presenter.onSearchViewShown()
            }

            override fun onSearchViewClosed() {
                presenter.onSearchViewClosed()
            }
        })
    }

    private fun setList() {
        val linearLayoutManager = LinearLayoutManager(this)
        countriesRecyclerView.layoutManager = linearLayoutManager
        adapter = CountryListAdapter(countryList) {
            presenter.onCountrySelected(it)
        }
        countriesRecyclerView.adapter = adapter
        countriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                presenter.onListScrolled(linearLayoutManager.findFirstVisibleItemPosition())
            }
        })
    }

    private fun setScrollToTopButton() {
        scrollToTop.setOnClickListener { presenter.onScrollToTopClicked() }
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

    override fun changeStatusBarColor(@ColorRes color: Int) {
        setStatusBarColor(color)
    }

    override fun scrollToTop() {
        countriesRecyclerView.scrollToPosition(0)
    }

    override fun setToolbarExpanded(expanded: Boolean) {
        appBarLayout.setExpanded(expanded)
    }

    override fun setScrollToTopButtonVisibility(visible: Boolean) {
        if (visible) {
            scrollToTop.visibility = View.VISIBLE
        } else {
            scrollToTop.visibility = View.GONE
        }
    }

    override fun goToCountryDetailedView(country: CountryListViewModel) {
        //todo take to detailed view
        toast("Implement this fucker")
    }
}
