package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import io.reactivex.Single

/**
 * Created by Rafa on 06/04/2018.
 */
class CountryListInteractor(private val countriesRepository: CountriesRepository) : CountryListMvp.Interactor {

    override fun getCountries(): Single<List<Country>> {
        return countriesRepository.getCountries()
    }
}