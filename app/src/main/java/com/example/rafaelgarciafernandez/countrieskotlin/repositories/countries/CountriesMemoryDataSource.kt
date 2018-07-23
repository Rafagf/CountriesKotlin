package com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Maybe

/**
 * Created by Rafa on 06/04/2018.
 */
class CountriesMemoryDataSource {

    private val countries: MutableList<Country> = mutableListOf()

    fun save(countries: List<Country>) {
        this.countries.addAll(countries)
    }

    fun clear() {
        countries.clear()
    }

    fun getCountries(): Maybe<List<Country>> {
        return if (countries.size > 0) {
            val shallowCopy: List<Country> = ArrayList<Country>(countries)
            Maybe.just(shallowCopy)
        } else {
            Maybe.empty()
        }
    }

    fun getCountryByName(name: String): Maybe<Country> {
        val country: Country? = countries.find { it.name == name }
        return if (country != null) {
            Maybe.just(country)
        } else {
            Maybe.empty()
        }
    }

    fun getCountryByAlpha3(alpha: String): Maybe<Country> {
        val country: Country? = countries.find { it.alpha3Code == alpha }
        return if (country != null) {
            Maybe.just(country)
        } else {
            Maybe.empty()
        }
    }
}