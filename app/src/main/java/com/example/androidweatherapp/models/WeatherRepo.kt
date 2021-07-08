package com.example.androidweatherapp.models

import com.example.androidweatherapp.viewmodel.ApiServiceBuilder
import com.example.androidweatherapp.viewmodel.JsonWeatherApi
import com.example.androidweatherapp.views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepo(
        private var useCase: UseCase
) {
    private val api: JsonWeatherApi = ApiServiceBuilder(
            JsonWeatherApi::class.java
    ).withApiBaseUrl(MainActivity.API_BASE_URL)
        .build()

    fun getWeatherForLocation(whereOnEarthId: Int) {
        val call = api.getWeatherForWhereOnEarthId(whereOnEarthId)
        call.enqueue(
                object : Callback<WeatherForLocation> {

                    override fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
                        useCase.onFailure(call, t)
                    }

                    override fun onResponse(
                            call: Call<WeatherForLocation>,
                            response: Response<WeatherForLocation>
                    ) {
                        useCase.onResponse(call, response)
                    }
                })
    }
}
