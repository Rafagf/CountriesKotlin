package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist.holder.CountryListViewHolder

/**
 * Created by Rafa on 06/04/2018.
 */

class CountryListAdapter(private val list: List<CountryListViewModel>, private val itemClick: (CountryListViewModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
        return CountryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryListViewHolder).bind(list[position])
        holder.itemView.setOnClickListener { itemClick.invoke(list[position]) }
    }

    override fun getItemCount(): Int = list.size

}