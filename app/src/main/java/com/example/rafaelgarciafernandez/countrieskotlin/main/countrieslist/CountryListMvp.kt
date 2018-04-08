package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Single

/**
 * Created by Rafa on 05/04/2018.
 */
interface CountryListMvp {
    interface View {
        fun showError()
        fun updateList(countries: List<CountryListViewModel>)

    }

    interface Interactor {
        fun getCountries(): Single<List<Country>>
    }

}