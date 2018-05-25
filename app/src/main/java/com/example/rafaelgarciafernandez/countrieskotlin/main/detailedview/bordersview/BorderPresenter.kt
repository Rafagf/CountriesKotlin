package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview

/**
 * Created by Rafa on 25/05/2018.
 */
class BorderPresenter(val view: BorderMvp.View) {

    fun bind(countries: List<String>) {
        if (countries.isNotEmpty()) {
            setBorderTitleVisibility(true)
            setBorders(countries)
        } else {
            setBorderTitleVisibility(false)
        }
    }

    private fun setBorderTitleVisibility(visible: Boolean) {
        view.setBorderTitleVisibility(visible)
    }

    private fun setBorders(countries: List<String>) {
        countries.forEach { view.addBorder(it) }
    }

    fun onCountryClicked(name: String) {
        view.goToCountryDetailedView(name)
    }
}