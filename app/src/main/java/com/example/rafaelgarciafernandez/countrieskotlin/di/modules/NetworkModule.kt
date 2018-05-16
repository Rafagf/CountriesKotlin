package com.example.rafaelgarciafernandez.countrieskotlin.di.modules

import com.example.rafaelgarciafernandez.countrieskotlin.api.request.country.CountryApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Rafa on 05/04/2018.
 */
@Module
class NetworkModule(baseUrl: String) {

    private var retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        retrofit = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()
    }

    @Provides
    @Singleton
    fun provideCountryApi(): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }

}