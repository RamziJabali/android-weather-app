package com.example.androidweatherapp

import com.example.androidweatherapp.models.Location
import com.example.androidweatherapp.models.WeatherForLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonWeatherApi {

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Call<WeatherForLocation>

    @GET("/api/location/search/?query={cityName}")
    fun getCitiesForQuery(@Path("cityName") cityName: String): Call<Location>


}
