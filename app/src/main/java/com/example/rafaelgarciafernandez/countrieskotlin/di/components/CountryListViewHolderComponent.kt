package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.CountryListViewHolderModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.scopes.PerViewHolder
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder.CountryListViewHolder
import dagger.Component

/**
 * Created by Rafa on 09/04/2018.
 */
@PerViewHolder
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(CountryListViewHolderModule::class))
interface CountryListViewHolderComponent {
    fun inject(holder: CountryListViewHolder)
}