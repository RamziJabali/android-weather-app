package com.example.androidweatherapp.viewmodel

import android.view.View
import androidx.annotation.VisibleForTesting
import com.example.androidweatherapp.models.Model
import com.example.androidweatherapp.models.UseCase
import com.example.androidweatherapp.models.WeatherForLocation
import com.example.androidweatherapp.views.MainActivity
import retrofit2.Call
import retrofit2.Response

class ViewModel(
    private var view: MainActivity
) {

    private lateinit var viewState: ViewState

    private val useCase = UseCase(this)
    fun startApp() {
        viewState = ViewState()
        getWeather()
        useCase.getWeatherForLocation(Model.sanfranWOEID)
        viewState.copy(isLoadingDialog = false)
        invalidateView()

    }

    private fun invalidateView() {
        view.setNewViewState(viewState)
    }

    private fun getWeather() {
        with(viewState) {
            viewOfText = View.GONE
            isLoadingDialog = true
        }
        invalidateView()
    }

    fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
        with(viewState) {
            isLoadingDialog = false
            didCallFail = true
            viewOfText = View.GONE
            onFailureMessage = useCase.onFailureMessage(t)
        }
        invalidateView()
    }

    fun onResponse(
        call: Call<WeatherForLocation>,
        response: Response<WeatherForLocation>
    ) {
        with(viewState) {
            isLoadingDialog = false
            viewOfText = View.VISIBLE
            if (!response.isSuccessful) {
                failedResponseMessage = "Code: " + response.code()
                invalidateView()
                return
            }
            val weatherForLocation = response.body()
            with(useCase) {
                cityTitle = weatherForLocation!!.cityTitle
                title = weatherForLocation.parentRegion.title
                theTemp = getTemp(weatherForLocation)
                airPressure =
                    weatherForLocation.consolidatedWeather[0].airPressure.toString()
                humidity = weatherForLocation.consolidatedWeather[0].humidity.toString()
                windSpeed = getWindSpeed(weatherForLocation)

            }
        }
        invalidateView()
    }
}