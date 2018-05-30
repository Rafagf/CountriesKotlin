package com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.bordersview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rafaelgarciafernandez.countrieskotlin.R
import com.example.rafaelgarciafernandez.countrieskotlin.di.components.DaggerBorderViewComponent
import com.example.rafaelgarciafernandez.countrieskotlin.di.modules.BorderViewModule
import com.example.rafaelgarciafernandez.countrieskotlin.main.detailedview.DetailedCountryActivity
import kotlinx.android.synthetic.main.borders_view.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by Rafa on 25/05/2018.
 */
class BordersView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BordersMvp.View {

    @Inject
    lateinit var presenter: BorderPresenter

    init {
        init()
        initView()
    }

    private fun init() {
        DaggerBorderViewComponent.builder()
                .borderViewModule(BorderViewModule(this))
                .build()
                .inject(this)
        presenter = BorderPresenter(this)
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.borders_view, null)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view.layoutParams = params
        addView(view)
    }

    fun bind(countries: List<String>) {
        presenter.bind(countries)
    }

    override fun setBorderTitleVisibility(visible: Boolean) {
        bordersTextView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun addBorder(countryName: String) {
        val border = View.inflate(context, R.layout.border_view, null) as TextView
        border.text = countryName
        bordersLinearLayout.addView(border)
        border.setOnClickListener { presenter.onCountryClicked(countryName) }
    }

    override fun goToCountryDetailedView(name: String) {
        context.startActivity<DetailedCountryActivity>(DetailedCountryActivity.COUNTRY_NAME_TAG to name)
    }
}