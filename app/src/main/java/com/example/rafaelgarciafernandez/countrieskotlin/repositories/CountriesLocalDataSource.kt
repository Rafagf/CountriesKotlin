package com.example.rafaelgarciafernandez.countrieskotlin.repositories

import android.content.SharedPreferences
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.utils.edit
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Maybe
import java.lang.reflect.Type


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
            val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
            val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
            val countries: List<Country>? = adapter.fromJson(countriesInJson)
            if (countries != null) {
                return Maybe.just(countries)
            }
        }

        return Maybe.empty()
    }

    fun getCountryByName(name: String): Maybe<Country> {
        val countriesInJson = getCountriesJsonFromSharedPreferences()
        if (countriesInJson != null) {
            val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
            val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
            val countries: List<Country>? = adapter.fromJson(countriesInJson)
            if (countries != null) {
                for (country in countries) {
                    if (country.name == name) {
                        return Maybe.just(country)
                    }
                }
            }
        }

        return Maybe.empty()
    }

    fun getCountryByAlpha3(alpha: String): Maybe<Country> {
        val countriesInJson = getCountriesJsonFromSharedPreferences()
        if (countriesInJson != null) {
            val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
            val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
            val countries: List<Country>? = adapter.fromJson(countriesInJson)
            if (countries != null) {
                for (country in countries) {
                    if (country.alpha3Code == alpha) {
                        return Maybe.just(country)
                    }
                }
            }
        }

        return Maybe.empty()
    }

    fun save(countries: List<Country>) {
        val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
        val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
        val countriesJson = adapter.toJson(countries)
        saveCountriesJsonInSharedPreferences(countriesJson)
    }

    fun clear() {
        sharedPreferences.edit {
            remove(COUNTRIES_JSON)
        }
    }

    private fun saveCountriesJsonInSharedPreferences(json: String) {
        sharedPreferences.edit {
            putString(COUNTRIES_JSON, json)
        }
    }

    private fun getCountriesJsonFromSharedPreferences(): String? {
        return sharedPreferences.getString(COUNTRIES_JSON, null)
    }

}