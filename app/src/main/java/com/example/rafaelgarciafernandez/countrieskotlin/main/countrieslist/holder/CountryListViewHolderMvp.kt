package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder

/**
 * Created by Rafa on 06/04/2018.
 */
interface CountryListViewHolderMvp {
    interface View {
        fun setName(name: String)

        fun setContinent(continent: String)

        fun setPopulation(population: String)

        fun setFlag(url: String)
    }
}