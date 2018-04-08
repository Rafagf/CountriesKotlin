package com.example.rafaelgarciafernandez.countrieskotlin.repositories

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
            val shallowCopy = ArrayList<Country>(countries)
            Maybe.just(shallowCopy)
        } else {
            Maybe.empty()
        }
    }

    fun getCountryByName(name: String): Maybe<Country> {
        for (country in countries) {
            if (country.name == name) {
                return Maybe.just(country)
            }
        }

        return Maybe.empty()
    }

    fun getCountryByAlpha3(alpha: String): Maybe<Country> {
        for (country in countries) {
            if (country.alpha3Code == alpha) {
                return Maybe.just(country)
            }
        }

        return Maybe.empty()
    }
}