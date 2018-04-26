package com.example.rafaelgarciafernandez.countrieskotlin

import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerApplicationComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.ApplicationModule
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.NetworkModule
import io.appflate.restmock.RESTMockServer

/**
 * Created by Rafa on 26/04/2018.
 */
class TestApplication : MyApplication() {

    fun createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(RESTMockServer.getUrl()))
                .build()
    }
}