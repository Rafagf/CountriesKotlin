package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test

/**
 * Created by Rafa on 25/05/2018.
 */
class BorderPresenterTest {

    private val view: BorderMvp.View = mock()
    private val presenter: BorderPresenter = BorderPresenter(view)

    @Test
    fun given_number_of_borders_is_greater_than_zero_when_started_then_show_borders() {
        val borders = listOf("Spain", "Portugal")

        presenter.bind(borders)

        verify(view).setBorderTitleVisibility(true)
        verify(view).addBorder("Spain")
        verify(view).addBorder("Portugal")
        verifyNoMoreInteractions(view)
    }

    @Test
    fun given_number_of_borders_is_zero_when_started_then_hide_everything() {
        val borders = emptyList<String>()

        presenter.bind(borders)

        verify(view).setBorderTitleVisibility(false)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onCountryClicked() {
        presenter.onCountryClicked("Portugal")

        verify(view).goToCountryDetailedView("Portugal")
        verifyNoMoreInteractions(view)
    }
}