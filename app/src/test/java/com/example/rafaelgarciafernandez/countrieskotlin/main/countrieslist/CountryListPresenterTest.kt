package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by Rafa on 26/04/2018.
 */
class CountryListPresenterTest {

    //todo fix this
//    @ClassRule
//    val schedulers = RxImmediateSchedulerRule()

    private lateinit var view: CountryListMvp.View
    private lateinit var interactor: CountryListMvp.Interactor
    private lateinit var presenter: CountryListPresenter

    @Before
    fun setUp() {
        view = mock(CountryListMvp.View::class.java)
        interactor = mock(CountryListMvp.Interactor::class.java)
        presenter = CountryListPresenter(view, interactor)
    }

    @Test
    fun when_it_starts_then_it_get_countries() {
        `when`(interactor.getCountries()).thenReturn(Single.never())

        presenter.init()

        verify(interactor).getCountries()
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun given_countries_successfully_fetched_when_started_then_it_updates_list() {
        val countries: List<Country> = listOf(mock(Country::class.java), mock(Country::class.java))
        `when`(interactor.getCountries()).thenReturn(Single.just(countries))

        presenter.init()

        verify(interactor).getCountries()
        verify(view).updateList(ArgumentMatchers.anyList())
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun given_countries_errored_when_started_then_it_shows_error() {
        `when`(interactor.getCountries()).thenReturn(Single.error(Throwable()))

        presenter.init()

        verify(interactor).getCountries()
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_retry_is_clicked_then_get_countries() {
        `when`(interactor.getCountries()).thenReturn(Single.never())

        presenter.retry()

        verify(interactor.getCountries())
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_query_text_is_submitted_then_update_list() {
        presenter.onQueryTextSubmit("Spain")

        verify(view).updateList(ArgumentMatchers.anyList())
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_query_text_is_changed_then_update_list() {
        presenter.onQueryTextChange("Spain")

        verify(view).updateList(ArgumentMatchers.anyList())
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_search_is_opened_then_change_status_bar_color() {
        presenter.onSearchViewShown()

        verify(view).changeStatusBarColor(R.color.plain_grey)
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_search_is_closed_then_change_status_bar_color() {
        presenter.onSearchViewClosed()

        verify(view).changeStatusBarColor(R.color.color_primary)
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_scroll_to_top_button_clicked_then_scroll_to_top() {
        presenter.onScrollToTopClicked()

        verify(view).scrollToTop()
        verify(view).setToolbarExpanded(true)
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun given_first_country_is_visible_when_user_scrolled_list_then_hide_go_to_top_button() {
        presenter.onListScrolled(0)

        verify(view).setScrollToTopButtonVisibility(false)
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun given_first_country_is_not_visible_when_user_scrolled_list_then_hide_go_to_top_button() {
        presenter.onListScrolled(1)

        verify(view).setScrollToTopButtonVisibility(true)
        verifyNoMoreInteractions(view, interactor)
    }

    @Test
    fun when_country_is_selected_then_open_detailed_view() {
        val country = mock(CountryListViewModel::class.java)

        presenter.onCountrySelected(country)

        verify(view).goToCountryDetailedView(country)
    }
}