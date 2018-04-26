package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rafaelgarciafernandez.countrieskotlin.MyApplication
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerCountryListViewHolderComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.CountryListViewHolderModule
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.CountryListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_card.view.*
import javax.inject.Inject

/**
 * Created by Rafa on 06/04/2018.
 */
class CountryListViewHolder(val view: View) : RecyclerView.ViewHolder(view), CountryListViewHolderMvp.View {

    private val nameTextView: TextView = itemView.nameTextView
    private val continentTextView: TextView = itemView.continentTextView
    private val populationTextView: TextView = itemView.populationTextView
    private val flagImageView: ImageView = itemView.flagImageView

    @Inject
    lateinit var presenter: CountryListViewHolderPresenter

    init {
        val applicationComponent = (itemView.context.applicationContext as MyApplication).applicationComponent
        DaggerCountryListViewHolderComponent.builder()
                .applicationComponent(applicationComponent)
                .countryListViewHolderModule(CountryListViewHolderModule(this))
                .build()
                .inject(this)
    }

    fun bind(country: CountryListViewModel) {
        presenter.bind(country)
    }

    override fun setName(name: String) {
        nameTextView.text = name
    }

    override fun setContinent(continent: String) {
        continentTextView.text = continent
    }

    override fun setPopulation(population: String) {
        populationTextView.text = population
    }

    override fun setFlag(url: String) {
        Picasso.with(itemView.context)
                .load(url)
                .into(flagImageView)
    }
}