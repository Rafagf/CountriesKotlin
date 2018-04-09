package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.FlagProvider
import com.example.rafaelgarciafernandez.countrieskotlin.di.providers.ResourcesProvider
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder.CountryListViewHolderMvp
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder.CountryListViewHolderPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Rafa on 09/04/2018.
 */
@Module
class CountryListViewHolderModule(private val view: CountryListViewHolderMvp.View) {

    @Provides
    fun providePresenter(resourcesProvider: ResourcesProvider, flagProvider: FlagProvider): CountryListViewHolderPresenter {
        return CountryListViewHolderPresenter(view, resourcesProvider, flagProvider)
    }
}