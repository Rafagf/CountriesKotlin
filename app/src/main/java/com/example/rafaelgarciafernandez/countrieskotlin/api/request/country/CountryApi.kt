package com.example.rafaelgarciafernandez.countrieskotlin.api.request.country

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rafa on 06/04/2018.
 */
interface CountryApi {

    @GET("name/{country}")
    fun getCountryByName(@Path("country") name: String): Single<List<Country>>

    @GET("alpha/{countryAlpha}")
    fun getCountryByAlpha3(@Path("countryAlpha") alpha: String): Single<Country>

    @GET("all")
    fun getAllCountries(): Single<List<Country>>
}