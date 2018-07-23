package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.TestApplication
import com.example.rafaelgarciafernandez.countrieskotlin.espresso.RecyclerViewItemCountAssertion
import com.example.rafaelgarciafernandez.countrieskotlin.espresso.matcherWithIndex
import com.example.rafaelgarciafernandez.countrieskotlin.getJsonFromAsset
import com.example.rafaelgarciafernandez.countrieskotlin.model.Country
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesLocalDataSource
import com.example.rafaelgarciafernandez.countrieskotlin.repositories.countries.CountriesMemoryDataSource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by Rafa on 07/05/2018.
 */
@RunWith(AndroidJUnit4::class)
class CountryListActivityTest {

    private lateinit var countriesLocalDataSource: CountriesLocalDataSource
    private lateinit var countriesMemoryDataSource: CountriesMemoryDataSource

    @get:Rule
    var activityTestRule = IntentsTestRule(CountryListActivity::class.java, false, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
        val countriesRepository = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).applicationComponent.countriesRepository
        countriesLocalDataSource = countriesRepository.localDataSource
        countriesMemoryDataSource = countriesRepository.memoryDataSource
        countriesLocalDataSource.clear()
        countriesMemoryDataSource.clear()
    }

    @Test
    fun it_shows_screen_when_launched() {
        activityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.listOfCountriesRoot)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun it_shows_2_countries_coming_from_remote_when_launched_and_there_is_no_data_in_memory_nor_local_data_sources() {
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith("all")).thenReturnFile(200, "json/list_of_countries_1.json")

        activityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(2))
    }

    @Test
    @Throws(IOException::class)
    fun it_shows_12_countries_coming_from_local_when_launched_and_there_is_data_in_local_data_source() {
        val countries = getCountriesFromJson("json/list_of_countries_2.json")
        countriesLocalDataSource.save(countries)

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(12))
    }

    @Test
    @Throws(IOException::class)
    fun it_shows_6_countries_coming_from_memory_when_launched_and_there_is_data_in_memory_data_source() {
        val countries = getCountriesFromJson("json/list_of_countries_3.json")
        countriesMemoryDataSource.save(countries)

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(6))
    }

    @Test
    fun given_search_input_is_spain_when_filtering_countries_then_display_only_spain() {
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json")

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.searchTextView)).perform(typeText("Spain"))
        onView(withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(1))
        val countryName = onView(matcherWithIndex(withId(R.id.nameTextView), 0))
        countryName.check(matches(withText("Spain")))
    }

    @Test
    fun given_search_input_is_ge_when_filtering_countries_then_display_germany_and_georgia() {
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json")

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.searchTextView)).perform(typeText("ge"))
        onView(withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(2))
        val firstCountryName = onView(matcherWithIndex(withId(R.id.nameTextView), 0))
        firstCountryName.check(matches(withText("Georgia")))
        val secondCountryName = onView(matcherWithIndex(withId(R.id.nameTextView), 1))
        secondCountryName.check(matches(withText("Germany")))
    }

    @Test
    fun given_search_input_is_something_dumb_when_filtering_countries_then_display_no_countries() {
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json")

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.searchTextView)).perform(typeText("dsgdskgs"))
        onView(withId(R.id.countriesRecyclerView)).check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    @Throws(IOException::class)
    fun when_user_scrolls_down_then_go_to_top_floating_button_is_visible() {
        val countries = getCountriesFromJson("json/list_of_countries_2.json")
        countriesLocalDataSource.save(countries)

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.countriesRecyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(10))

        onView(withId(R.id.scrollToTop)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(IOException::class)
    fun when_user_scrolls_down_and_then_to_top_then_go_to_top_floating_button_is_not_visible() {
        val countries = getCountriesFromJson("json/list_of_countries_2.json")
        countriesLocalDataSource.save(countries)
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.countriesRecyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.countriesRecyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.scrollToTop)).check(matches(not<View>(isDisplayed())))
    }

    @Test
    @Throws(IOException::class)
    fun when_user_scrolls_down_and_then_click_on_go_to_top_button_then_list_scrolls_to_top() {
        val countries = getCountriesFromJson("json/list_of_countries_2.json")
        countriesLocalDataSource.save(countries)
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.countriesRecyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.scrollToTop)).perform(click())
        val firstCountryName = onView(matcherWithIndex(withId(R.id.countryCardRoot), 0))
        firstCountryName.check(matches(isDisplayed()))
    }

    @Test
    fun when_search_item_is_clicked_then_go_to_detailed_view() {
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json")

        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.searchTextView)).perform(typeText("Spain"))
        onView(matcherWithIndex(withId(R.id.nameTextView), 0)).perform(click())
        onView(CoreMatchers.allOf<View>(CoreMatchers.instanceOf<Any>(TextView::class.java), withParent(withId(R.id.toolbar)))).check(matches(withText("Spain")))

    }

    @Test
    fun when_country_is_clicked_then_go_to_detailed_view() {
        val countries = getCountriesFromJson("json/list_of_countries_2.json")
        countriesLocalDataSource.save(countries)

        activityTestRule.launchActivity(Intent())

        onView(matcherWithIndex(withId(R.id.nameTextView), 0)).perform(click())
        onView(CoreMatchers.allOf<View>(CoreMatchers.instanceOf<Any>(TextView::class.java), withParent(withId(R.id.toolbar)))).check(matches(withText("Afghanistan")))
    }

    @Throws(IOException::class)
    private fun getCountriesFromJson(path: String): List<Country> {
        val json = getJsonFromAsset(path)
        if (json != null) {
            val type: Type = Types.newParameterizedType(List::class.java, Country::class.java)
            val adapter: JsonAdapter<List<Country>> = Moshi.Builder().build().adapter(type)
            val countries: List<Country>? = adapter.fromJson(json)
            if (countries != null) {
                return countries
            }
        }

        throw IOException("Could not read countries")
    }
}