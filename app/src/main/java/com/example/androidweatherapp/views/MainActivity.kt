package com.example.androidweatherapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.androidweatherapp.viewmodel.ApiServiceBuilder
import com.example.androidweatherapp.viewmodel.JsonWeatherApi
import com.example.androidweatherapp.R
import com.example.androidweatherapp.viewmodel.ViewModel
import com.example.androidweatherapp.viewmodel.ViewState

class MainActivity : AppCompatActivity(), ViewListener {

    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }

    private lateinit var api: JsonWeatherApi

    private val loadingDialog = LoadingDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = ApiServiceBuilder(
            JsonWeatherApi::class.java
        )
            .withApiBaseUrl(API_BASE_URL)
            .build()

        val viewModel = ViewModel(api, this)
        viewModel.startApp()
    }


    override fun setNewViewState(viewState: ViewState) {
        if (viewState.isLoadingDialog) loadingDialog.startLoadingDialog() else loadingDialog.dismissDialog()
        showViews(viewState.viewOfText)
        populateViewsWithText(viewState)
        return
    }

    private fun showViews(viewOfText: Int) {
        findViewById<TextView>(R.id.city).visibility = viewOfText
        findViewById<TextView>(R.id.state).visibility = viewOfText
        findViewById<TextView>(R.id.temperature).visibility = viewOfText
        findViewById<TextView>(R.id.pressureValueText).visibility = viewOfText
        findViewById<TextView>(R.id.humidityValueText).visibility = viewOfText
        findViewById<TextView>(R.id.windSpeedValueText).visibility = viewOfText
        findViewById<TextView>(R.id.pressureText).visibility = viewOfText
        findViewById<TextView>(R.id.humidityText).visibility = viewOfText
        findViewById<TextView>(R.id.windSpeedText).visibility = viewOfText
        findViewById<ImageView>(R.id.weatherImage).visibility = viewOfText
    }


    private fun populateViewsWithText(viewState: ViewState) {
        with(viewState) {
            findViewById<TextView>(R.id.city).text = cityTitle
            findViewById<TextView>(R.id.state).text = title
            findViewById<TextView>(R.id.temperature).text = theTemp
            findViewById<TextView>(R.id.pressureValueText).text = airPressure
            findViewById<TextView>(R.id.humidityValueText).text = humidity
            findViewById<TextView>(R.id.windSpeedValueText).text = windSpeed
        }
    }

    //    private fun showProgressBarAndLoadingText() {
//        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
//        findViewById<TextView>(R.id.loadingTextView).visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBarAndLoadingText() {
//        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
//        findViewById<TextView>(R.id.loadingTextView).visibility = View.GONE
//    }
}


