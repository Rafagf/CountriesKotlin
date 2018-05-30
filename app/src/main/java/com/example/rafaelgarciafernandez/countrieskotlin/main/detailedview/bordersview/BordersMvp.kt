package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview

/**
 * Created by Rafa on 25/05/2018.
 */
interface BordersMvp {
    interface View {
        fun setBorderTitleVisibility(visible: Boolean)
        fun addBorder(countryName: String)
        fun goToCountryDetailedView(name: String)

    }
}