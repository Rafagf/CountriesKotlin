package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListActivity
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.CountryListViewModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.scopes.PerActivity
import dagger.Component

/**
 * Created by Rafa on 05/04/2018.
 */
@PerActivity
@Component(
        modules = arrayOf(CountryListViewModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)

interface CountryListViewComponent {
    fun inject(activity: CountryListActivity)
}