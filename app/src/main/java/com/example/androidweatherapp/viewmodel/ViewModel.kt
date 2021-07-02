package com.example.androidweatherapp.viewmodel

import android.view.View
import com.example.androidweatherapp.models.WeatherForLocation
import com.example.androidweatherapp.views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel(
    private val api: JsonWeatherApi,
    private var view: MainActivity
) {

    private lateinit var viewState: ViewState

    fun startApp() {
        viewState = ViewState()
        getWeather(44418)
    }

    private fun invalidateView() {
        view.setNewViewState(viewState)
    }


    private fun getWeather(whereOnEarthId: Int) {
        with(viewState) {
            viewOfText = View.GONE
            isLoadingDialog = true
            invalidateView()
            val call = api.getWeatherForWhereOnEarthId(whereOnEarthId)
            call.enqueue(
                object : Callback<WeatherForLocation> {
                    override fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
                        isLoadingDialog = false
                        viewOfText = View.VISIBLE
                        onFailureMessage = t.message.toString()
                        invalidateView()
                    }

                    override fun onResponse(
                        call: Call<WeatherForLocation>,
                        response: Response<WeatherForLocation>
                    ) {
                        isLoadingDialog = false
                        viewOfText = View.VISIBLE
                        if (!response.isSuccessful) {
                            failedResponseMessage = "Code: " + response.code()
                            invalidateView()
                            return
                        }
                        val weatherForLocation = response.body()
                        cityTitle = weatherForLocation!!.cityTitle
                        title = weatherForLocation.parentRegion.title
                        theTemp =
                            weatherForLocation.consolidatedWeather[0].theTemp.toString() + " F"
                        airPressure =
                            weatherForLocation.consolidatedWeather[0].airPressure.toString()
                        humidity = weatherForLocation.consolidatedWeather[0].humidity.toString()
                        windSpeed = weatherForLocation.consolidatedWeather[0].windSpeed.toString()
                        invalidateView()
                    }
                })
        }
    }
}