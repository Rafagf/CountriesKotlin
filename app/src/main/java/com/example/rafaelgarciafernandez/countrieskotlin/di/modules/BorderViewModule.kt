package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview.BorderMvp
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview.BorderPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Rafa on 25/05/2018.
 */
@Module
class BorderViewModule(private val view: BorderMvp.View) {

    @Provides
    fun BorderPresenter(): BorderPresenter {
        return BorderPresenter(view)
    }
}