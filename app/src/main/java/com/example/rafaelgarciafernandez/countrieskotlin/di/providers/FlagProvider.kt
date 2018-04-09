package com.example.rafaelgarciafernandez.countrieskotlin.di.providers

/**
 * Created by Rafa on 09/04/2018.
 */

class FlagProvider {

    companion object {
        private val FLAGS_URL = "http://www.geonames.org/flags/x/"
    }

    fun getFlagUrl(code: String): String {
        return FLAGS_URL + code.toLowerCase() + ".gif"
    }
}
