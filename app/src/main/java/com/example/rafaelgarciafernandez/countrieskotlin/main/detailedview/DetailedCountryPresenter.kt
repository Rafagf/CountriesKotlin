package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.utils.toAreaFormat
import com.example.rafaelgarciafernandez.countrieskotlin.utils.toPopulationFormat
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rafa on 10/05/2018.
 */
class DetailedCountryPresenter(private val view: DetailedCountryMvp.View, private val interactor: DetailedCountryMvp.Interactor,
                               private val resourcesProvider: ResourcesProvider, private val flagProvider: FlagProvider) {

    private lateinit var countryViewModel: DetailedCountryViewModel
    private val compositeDisposable = CompositeDisposable()

    fun init(country: String) {
        fetchCountry(country)
    }

    fun stop() {
        compositeDisposable.clear()
    }

    private fun fetchCountry(country: String) {
        interactor.getCountry(country)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<Country> {
                    override fun onSuccess(country: Country) {
                        onFetchingCountrySucceed(country)

                    }

                    override fun onSubscribe(disposable: Disposable) {
                        compositeDisposable.add(disposable)
                    }

                    override fun onError(error: Throwable) {
                        onFetchingCountryFailed()
                    }

                })
    }

    private fun onFetchingCountryFailed() {
        view.showError()
    }

    private fun onFetchingCountrySucceed(country: Country) {
        countryViewModel = DetailedCountryViewModelMapper().mapFrom(country)
        setName()
        setFlag()
        setCapital()
        setContinent()
        setRegion()
        setMap()
        setPopulation()
        setArea()
        setDemonym()
        setNativeName()
        setBorderCountries()
    }

    private fun setName() {
        view.setName(countryViewModel.name)

    }

    private fun setFlag() {
        view.setFlag(flagProvider.getFlagUrl(countryViewModel.alpha2))
    }

    private fun setCapital() {
        val capital: String? = countryViewModel.capital
        if (capital == null || capital.isEmpty()) {
            view.setCapital("-")
        } else {
            view.setCapital(capital)
        }
    }

    private fun setContinent() {
        val continent: String? = countryViewModel.continent
        if (continent == null || continent.isEmpty()) {
            view.setContinent("-")
        } else {
            view.setContinent(continent)
        }
    }

    private fun setRegion() {
        val region: String? = countryViewModel.region
        if (region == null || region.isEmpty()) {
            view.setRegion("-")
        } else {
            view.setRegion(region)
        }
    }

    private fun setPopulation() {
        val population: String? = countryViewModel.population
        if (population == null || population.isEmpty()) {
            view.setPopulation(resourcesProvider.getText(R.string.population) + "0")
        } else {
            view.setPopulation(resourcesProvider.getText(R.string.population) + population.toLong().toPopulationFormat())
        }
    }

    private fun setArea() {
        val area: String? = countryViewModel.area
        if (area == null || area.isEmpty()) {
            view.setArea(resourcesProvider.getText(R.string.area) + "0 m²")
        } else {
            view.setArea(resourcesProvider.getText(R.string.area) + area.toFloat().toAreaFormat())
        }
    }

    private fun setDemonym() {
        val demonym: String? = countryViewModel.demonym
        if (demonym == null || demonym.isEmpty()) {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + "-")
        } else {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + demonym)
        }
    }

    private fun setNativeName() {
        val nativeName: String? = countryViewModel.nativeName
        if (nativeName == null || nativeName.isEmpty()) {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + "-")
        } else {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + nativeName)
        }
    }

    private fun setMap() {
        view.addMapMarker(countryViewModel.latLng, countryViewModel.name)
    }

    private fun setBorderCountries() {
        interactor.getBorderCountriesName(countryViewModel.borderCountryAlphaList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<List<String>> {
                    override fun onSuccess(borderCountries: List<String>) {
                        onFetchingBorderCountriesSucceed(borderCountries)
                    }

                    override fun onSubscribe(disposable: Disposable) {
                        compositeDisposable.add(disposable)

                    }

                    override fun onError(error: Throwable) {
                        onFetchingBorderCountriesFailed()
                    }
                })
    }

    private fun onFetchingBorderCountriesSucceed(borderCountries: List<String>) {
        view.setBorders(borderCountries)
        view.setBordersVisibility(true)
    }

    private fun onFetchingBorderCountriesFailed() {
        view.setBordersVisibility(false)
    }

    fun onRetryClicked(countryName: String) {
        fetchCountry(countryName)
    }
}