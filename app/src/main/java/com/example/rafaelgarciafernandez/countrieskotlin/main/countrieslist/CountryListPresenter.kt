package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Rafa on 06/04/2018.
 */
class CountryListPresenter(private val view: CountryListMvp.View,
                           private val interactor: CountryListMvp.Interactor) {

    private val countryList = ArrayList<CountryListViewModel>()
    private val compositeDisposable = CompositeDisposable()

    fun init() {
        fetchCountries()
    }

    fun stop() {
        compositeDisposable.clear()
    }

    private fun fetchCountries() {
        interactor.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<List<Country>> {
                    override fun onSubscribe(disposable: Disposable) {
                        compositeDisposable.add(disposable)
                    }

                    override fun onSuccess(countries: List<Country>) {
                        onFetchingCountriesSucceed(countries)
                    }

                    override fun onError(e: Throwable) {
                        onFetchingCountriesFailed()
                    }
                })

    }

    private fun onFetchingCountriesFailed() {
        view.showError()
    }

    private fun onFetchingCountriesSucceed(countries: List<Country>) {
        val mapper = CountryListViewModelMapper()
        val countriesViewModel = mapper.mapFrom(countries)
        countryList.clear()
        countryList.addAll(countriesViewModel)
        view.updateList(countriesViewModel)
    }

    fun retry() {
        fetchCountries()
    }

    fun onQueryTextSubmit(query: String) {
        search(query)
    }

    fun onQueryTextChange(newText: String) {
        search(newText)
    }

    private fun search(query: String) {
        val filteredCountries = ArrayList<CountryListViewModel>()
        for (country in countryList) {
            if (country.name.toLowerCase().startsWith(query.toLowerCase())) {
                filteredCountries.add(country)
            }
        }

        view.updateList(filteredCountries)
    }

    fun onSearchViewShown() {
        view.changeStatusBarColor(R.color.plain_grey)
    }

    fun onSearchViewClosed() {
        view.changeStatusBarColor(R.color.color_primary)
    }

    fun onScrollToTopClicked() {
        view.scrollToTop()
        view.setToolbarExpanded(true)
    }

    fun onListScrolled(firstVisibleItem: Int) {
        view.setScrollToTopButtonVisibility(firstVisibleItem > 0)
    }

    fun onCountrySelected(country: CountryListViewModel) {
        view.goToCountryDetailedView(country)
    }
}