package com.example.rafaelgarciafernandez.countrieskotlin.repositories

import android.content.SharedPreferences
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Maybe

/**
 * Created by Rafa on 06/04/2018.
 */
class CountriesLocalDataSource(private val sharedPreferences: SharedPreferences) {

    private companion object {
        private const val COUNTRIES_JSON = "countries_json"
    }

    fun getCountries(): Maybe<List<Country>> {
        val countriesInJson = getCountriesJsonFromSharedPreferences()
        if (countriesInJson != null) {
            //todo return Maybe.just(countries)

        }

        return Maybe.empty()
    }

    fun getCountryByName(name: String): Maybe<Country> {
        val countriesInJson = getCountriesJsonFromSharedPreferences()
        if (countriesInJson != null) {
           //todo return

        }

        return Maybe.empty()
    }

    fun getCountryByAlpha3(alpha: String): Maybe<Country> {
        val countriesInJson = getCountriesJsonFromSharedPreferences()
        if (countriesInJson != null) {
           //todo return
        }

        return Maybe.empty()
    }

    fun save(countries: List<Country>) {
      //todo

    }

    fun clear() {
        sharedPreferences.edit().remove(COUNTRIES_JSON).apply()
    }

    private fun saveCountriesJsonInSharedPreferences(json: String) {
        //todo use kotlin ktx
        sharedPreferences.edit().putString(COUNTRIES_JSON, json).apply()
    }

    private fun getCountriesJsonFromSharedPreferences(): String? {
        return sharedPreferences.getString(COUNTRIES_JSON, null)
    }

}