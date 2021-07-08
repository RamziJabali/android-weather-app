package com.example.androidweatherapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.VisibleForTesting
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

    private lateinit var loadingDialog: LoadingDialog

    private lateinit var viewModel: ViewModel

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var cityTextView: TextView
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var stateTextView: TextView
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var temperatureTextView: TextView
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var pressureValueTextView: TextView
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var humidityValueTextView: TextView
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var windSpeedValueTextView: TextView

    private lateinit var windPressureText: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var windSpeedTextView: TextView
    private lateinit var onFailureTextView: TextView
    private lateinit var weatherImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpFieldMembers()

        viewModel.startApp()
    }

    override fun setNewViewState(viewState: ViewState) {
        if (viewState.isLoadingDialog) loadingDialog.show() else loadingDialog.hide()
        if(viewState.didCallFail) onFailureTextView.visibility = View.VISIBLE else onFailureTextView.visibility = View.GONE
        setTextAndImageViewVisibility(viewState.viewOfText)
        populateViewsWithText(viewState)
        return
    }

    private fun setUpFieldMembers() {
        viewModel = ViewModel(this)
        loadingDialog = LoadingDialog(this)
        api = ApiServiceBuilder(
            JsonWeatherApi::class.java
        )
            .withApiBaseUrl(API_BASE_URL)
            .build()
        cityTextView = findViewById(R.id.city)
        stateTextView = findViewById(R.id.state)
        temperatureTextView = findViewById(R.id.temperature)
        pressureValueTextView = findViewById(R.id.pressureValueText)
        humidityValueTextView = findViewById(R.id.humidityValueText)
        windSpeedValueTextView = findViewById(R.id.windSpeedValueText)
        windPressureText = findViewById(R.id.pressureText)
        humidityTextView = findViewById(R.id.humidityText)
        windSpeedTextView = findViewById(R.id.windSpeedText)
        onFailureTextView = findViewById(R.id.onFailureText)
        weatherImageView = findViewById(R.id.weatherImage)
    }


    private fun setTextAndImageViewVisibility(viewOfText: Int) {
        cityTextView.visibility = viewOfText
        stateTextView.visibility = viewOfText
        temperatureTextView.visibility = viewOfText
        pressureValueTextView.visibility = viewOfText
        humidityValueTextView.visibility = viewOfText
        windSpeedValueTextView.visibility = viewOfText
        windPressureText.visibility = viewOfText
        humidityTextView.visibility = viewOfText
        windSpeedTextView.visibility = viewOfText
        weatherImageView.visibility = viewOfText
    }

    private fun populateViewsWithText(viewState: ViewState) {
        with(viewState) {
            cityTextView.text = cityTitle
            stateTextView.text = title
            temperatureTextView.text = theTemp
            pressureValueTextView.text = airPressure
            humidityValueTextView.text = humidity
            windSpeedValueTextView.text = windSpeed
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


