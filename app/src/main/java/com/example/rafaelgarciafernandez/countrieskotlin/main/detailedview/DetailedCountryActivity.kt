package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.rafaelgarciafernandez.countrieskotlin.MyApplication
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerDetailedCountryViewComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.DetailedCountryViewModule
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailed_country.*
import javax.inject.Inject

class DetailedCountryActivity : AppCompatActivity(), DetailedCountryMvp.View, OnMapReadyCallback {

    companion object {
        const val COUNTRY_NAME_TAG = "country_name"
    }

    @Inject
    lateinit var presenter: DetailedCountryPresenter

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay)
        setContentView(R.layout.activity_detailed_country)
        initViews(savedInstanceState)
        init(intent.getStringExtra(COUNTRY_NAME_TAG))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
        mapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun initViews(savedInstanceState: Bundle?) {
        setToolbar()
        setMap(savedInstanceState)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setMap(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mapView.onCreate(savedInstanceState)
        }

        mapView?.getMapAsync(this)
    }


    private fun init(country: String) {
        val applicationComponent = (application as MyApplication).applicationComponent
        DaggerDetailedCountryViewComponent.builder()
                .applicationComponent(applicationComponent)
                .detailedCountryViewModule(DetailedCountryViewModule(this))
                .build()
                .inject(this)
        presenter.init(country)
    }

    override fun setFlag(url: String) {
        Picasso.with(this)
                .load(url)
                .into(flagImageView)
    }

    override fun setName(name: String) {
        supportActionBar?.title = name
    }

    override fun setContinent(continent: String) {
        continentTextView.text = continent
    }

    override fun setRegion(region: String) {
        regionTextView.text = region
    }

    override fun setCapital(capital: String) {
        capitalTextView.text = capital
    }

    override fun setPopulation(population: String) {
        populationTextView.text = population
    }

    override fun setArea(area: String) {
        areaTextView.text = area
    }

    override fun setDemonym(demonym: String) {
        demonymTextView.text = demonym
    }

    override fun setNativeName(nativeName: String) {
        nativeNameTextView.text = nativeName
    }

    override fun addMapMarker(latLng: LatLng, country: String) {
        googleMap.addMarker(MarkerOptions()
                .position(latLng)
                .title(country))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3f))

    }

    override fun setBorders(borderCountries: List<String>) {
        bordersView.bind(borderCountries)
    }

    override fun setBordersVisibility(visibility: Boolean) {
        bordersView.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun showError() {
        Snackbar.make(findViewById(android.R.id.content), R.string.there_was_an_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) { view -> presenter.onRetryClicked(intent.getStringExtra(COUNTRY_NAME_TAG)) }
                .show()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.setAllGesturesEnabled(false)
    }
}
