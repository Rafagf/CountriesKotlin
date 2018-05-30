package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import com.google.android.gms.maps.model.LatLng

data class DetailedCountryViewModel(val name: String, val alpha2: String, val alpha3: String, val capital: String? = null,
                                    val continent: String? = null, val region: String? = null, val area: String? = null,
                                    val nativeName: String? = null, val population: String? = null, val demonym: String? = null,
                                    val latLng: LatLng, val borderCountryAlphaList: List<String> = listOf())
