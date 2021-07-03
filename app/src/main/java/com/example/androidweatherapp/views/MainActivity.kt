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

    private lateinit var loadingDialog: LoadingDialog

    private lateinit var viewModel: ViewModel

    private lateinit var cityTextView: TextView
    private lateinit var stateTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var pressureValueTextView: TextView
    private lateinit var humidityValueTextView: TextView
    private lateinit var windSpeedValueTextView: TextView
    private lateinit var windPressureText: TextView
    private lateinit var humidityTextView:TextView
    private lateinit var windSpeedTextView:TextView
    private lateinit var weatherImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpFieldMembers()

        viewModel.startApp()
    }

    private fun setUpFieldMembers(){
        viewModel = ViewModel(this)
        loadingDialog = LoadingDialog(this)
        api = ApiServiceBuilder(
            JsonWeatherApi::class.java
        )
            .withApiBaseUrl(API_BASE_URL)
            .build()
        cityTextView = findViewById<TextView>(R.id.city)
        stateTextView = findViewById<TextView>(R.id.state)
        temperatureTextView = findViewById<TextView>(R.id.temperature)
        pressureValueTextView = findViewById<TextView>(R.id.pressureValueText)
        humidityValueTextView = findViewById<TextView>(R.id.humidityValueText)
        windSpeedValueTextView = findViewById<TextView>(R.id.windSpeedValueText)
        windPressureText = findViewById<TextView>(R.id.pressureText)
        humidityTextView = findViewById<TextView>(R.id.humidityText)
        windSpeedTextView = findViewById<TextView>(R.id.windSpeedText)
        weatherImageView = findViewById<ImageView>(R.id.weatherImage)

    }

    override fun setNewViewState(viewState: ViewState) {
        if (viewState.isLoadingDialog) loadingDialog.show() else loadingDialog.hide()
        showViews(viewState.viewOfText)
        populateViewsWithText(viewState)
        return
    }

    private fun showViews(viewOfText: Int) {
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


