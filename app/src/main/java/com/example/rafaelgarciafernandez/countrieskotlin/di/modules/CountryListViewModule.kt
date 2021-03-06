package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListInteractor
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListMvp
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListPresenter
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Rafa on 05/04/2018.
 */
@Module
class CountryListViewModule(private val view: CountryListMvp.View) {

    @Provides
    fun providesCountryListInteractor(countriesRepository: CountriesRepository): CountryListInteractor {
        return CountryListInteractor(countriesRepository)
    }

    @Provides
    fun providesCountryListPresenter(interactor: CountryListInteractor): CountryListPresenter {
        return CountryListPresenter(view, interactor)
    }
}