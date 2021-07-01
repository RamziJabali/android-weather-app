package com.example.androidweatherapp.viewmodel

import com.example.androidweatherapp.models.Location
import com.example.androidweatherapp.models.WeatherForLocation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonWeatherApi {

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Call<WeatherForLocation>

    @GET("/api/location/search")
    fun getCitiesForQuery(@Query("cityName") cityName: String): Call<List<Location>>


}
