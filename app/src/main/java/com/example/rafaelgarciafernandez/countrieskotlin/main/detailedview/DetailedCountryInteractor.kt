package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Rafa on 25/05/2018.
 */
class DetailedCountryInteractor(private val countriesRepository: CountriesRepository) : DetailedCountryMvp.Interactor {

    override fun getCountry(name: String): Single<Country> {
        return countriesRepository.getCountriesByName(name)
    }

    override fun getBorderCountriesName(alphaCountryList: List<String>): Single<List<String>> {
        return Observable.fromIterable(alphaCountryList)
                .flatMap { countryAlpha ->
                    countriesRepository.getCountryByAlpha3(countryAlpha)
                            .toObservable()
                            .flatMap { Observable.just(it.name) }
                }
                .toList()
    }
}