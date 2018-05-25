package com.example.rafaelgarciafernandez.countrieskotlin.di.components

import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.BorderViewModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.scopes.PerCustomView
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview.BorderView
import dagger.Component

/**
 * Created by Rafa on 25/05/2018.
 */
@PerCustomView
@Component(
        modules = arrayOf(BorderViewModule::class)
        )

interface BorderViewComponent {
    fun inject(view: BorderView)
}