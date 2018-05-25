package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rafaelgarciafernandez.countrieskotlin.MyApplication
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerDetailedCountryViewComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.DetailedCountryViewModule
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class DetailedCountryActivity : AppCompatActivity(), DetailedCountryMvp.View {

    companion object {
        val COUNTRY_NAME_TAG = "country_name"
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
//        mapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
//        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
//        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
//        mapView.onLowMemory()
    }

    private fun initViews(savedInstanceState: Bundle?) {
        setToolbar()
        setMap(savedInstanceState)
    }

    private fun setToolbar() {
//        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
    }

    private fun setMap(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
//        mapView.onCreate(savedInstanceState)
        }
//        mapView?.getMapAsync(this)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setContinent(continent: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRegion(region: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCapital(capital: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPopulation(population: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setArea(area: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDemonym(demonym: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNativeName(nativeName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addMapMarker(latLng: LatLng, country: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBorders(borderCountries: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBordersVisibility(visibility: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
