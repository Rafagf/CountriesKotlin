package com.example.rafaelgarciafernandez.countrieskotlin.main.countrieslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rafaelgarciafernandez.countrieskotlin.R

class CountryListActivity : AppCompatActivity(), CountryListMvp.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
