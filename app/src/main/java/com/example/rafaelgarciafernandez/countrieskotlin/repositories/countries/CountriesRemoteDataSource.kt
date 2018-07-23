package com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries

import com.example.rafaelgarciafernandez.countrieskotlin.api.request.country.CountryApi
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Single

/**
 * Created by Rafa on 06/04/2018.
 */
class CountriesRemoteDataSource(private val countryApi: CountryApi) {

    fun getCountries(): Single<List<Country>> {
        return countryApi.getAllCountries()
    }

    fun getCountryByName(name: String): Single<Country> {
        return countryApi.getCountryByName(name)
                .flatMap { Single.just(it[0]) }
    }

    fun getCountryByAlpha3(alpha: String): Single<Country> {
        return countryApi.getCountryByAlpha3(alpha)
    }
}