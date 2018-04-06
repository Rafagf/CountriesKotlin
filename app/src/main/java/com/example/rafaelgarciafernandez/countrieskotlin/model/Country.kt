package com.example.rafaelgarciafernandez.countrieskotlin.model

import com.squareup.moshi.Json

/**
 * Created by Rafa on 05/04/2018.
 */
data class Country(val name: String, val nativeName: String, val alpha2Code: String,
                   val alpha3Code: String, val capital: String?, val population: String?,
                   val area: String?, val demonym: String?, val latLng: List<Double>,
                   @Json(name = "region") val continent: String, @Json(name = "subregion") val region: String?, @Json(name = "borders") val borderCountryAlphaList: List<String> = listOf())