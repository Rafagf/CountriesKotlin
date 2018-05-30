package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.DetailedCountryViewModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.scopes.PerActivity
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.DetailedCountryActivity
import dagger.Component

/**
 * Created by Rafa on 05/04/2018.
 */
@PerActivity
@Component(
        modules = arrayOf(DetailedCountryViewModule::class),
        dependencies = arrayOf(ApplicationComponent::class)
)

interface DetailedCountryViewComponent {
    fun inject(activity: DetailedCountryActivity)
}