package com.example.androidweatherapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.androidweatherapp.viewmodel.ApiServiceBuilder
import com.example.androidweatherapp.viewmodel.JsonWeatherApi
import com.example.androidweatherapp.R
import com.example.androidweatherapp.models.Location
import com.example.androidweatherapp.models.WeatherForLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }

    var loadingDialog = LoadingDialog(this)

    lateinit var api: JsonWeatherApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = ApiServiceBuilder(
            JsonWeatherApi::class.java
        )
            .withApiBaseUrl(API_BASE_URL)
            .build()


        getCountryAndCityName(2487956)
    }

    private fun getCountryAndCityName(whereOnEarthId: Int) {
        stopShowingViewsWhileLoading()
        loadingDialog.startLoadingDialog()
//        showProgressBarAndLoadingText()
        val call = api.getWeatherForWhereOnEarthId(whereOnEarthId)
        call.enqueue(
            object : Callback<WeatherForLocation> {
                override fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
                    loadingDialog.dismissDialog()
//                    hideProgressBarAndLoadingText()
                    showViews()
                    findViewById<TextView>(R.id.city).text = t.message
                    findViewById<TextView>(R.id.state).text = t.message
                    findViewById<TextView>(R.id.temperature).text = t.message
                }

                override fun onResponse(
                    call: Call<WeatherForLocation>,
                    response: Response<WeatherForLocation>
                ) {
                    loadingDialog.dismissDialog()
//                    hideProgressBarAndLoadingText()
                    showViews()
                    if (!response.isSuccessful) {
                        findViewById<TextView>(R.id.city).text = "Code: " + response.code()
                        findViewById<TextView>(R.id.state).text = "Code: " + response.code()
                        return
                    }
                    val weatherForLocation = response.body()
                    findViewById<TextView>(R.id.city).text = weatherForLocation!!.cityTitle
                    findViewById<TextView>(R.id.state).text = weatherForLocation.parentRegion.title
                    findViewById<TextView>(R.id.temperature).text =
                        weatherForLocation.consolidatedWeather[0].theTemp.toString() + " F"
                    findViewById<TextView>(R.id.pressureValueText).text =
                        weatherForLocation.consolidatedWeather[0].airPressure.toString()
                    findViewById<TextView>(R.id.humidityValueText).text =
                        weatherForLocation.consolidatedWeather[0].humidity.toString()
                    findViewById<TextView>(R.id.windSpeedValueText).text =
                        weatherForLocation.consolidatedWeather[0].windSpeed.toString()
                }
            })
    }

    private fun getCities() {
        val call = api.getCitiesForQuery("london") //error here
        call.enqueue(
            object : Callback<List<Location>> {
                override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                    findViewById<TextView>(R.id.city).text = t.message
                    findViewById<TextView>(R.id.state).text = t.message
                    findViewById<TextView>(R.id.temperature).text = t.message
                }

                override fun onResponse(
                    call: Call<List<Location>>,
                    response: Response<List<Location>>
                ) {
                    if (!response.isSuccessful) {
                        findViewById<TextView>(R.id.city).text = "Code: " + response.code()
                        findViewById<TextView>(R.id.state).text = "Code: " + response.code()
                        return
                    }
                    val locationOfCity = response.body()
                    findViewById<TextView>(R.id.city).text = locationOfCity!![0].cityTitle
                    findViewById<TextView>(R.id.state).text = locationOfCity[0].location_type
                }

            })
    }

    private fun stopShowingViewsWhileLoading() {
        findViewById<TextView>(R.id.city).visibility = View.GONE
        findViewById<TextView>(R.id.state).visibility = View.GONE
        findViewById<TextView>(R.id.temperature).visibility = View.GONE
        findViewById<TextView>(R.id.pressureValueText).visibility = View.GONE
        findViewById<TextView>(R.id.humidityValueText).visibility = View.GONE
        findViewById<TextView>(R.id.windSpeedValueText).visibility = View.GONE
        findViewById<TextView>(R.id.pressureText).visibility = View.GONE
        findViewById<TextView>(R.id.humidityText).visibility = View.GONE
        findViewById<TextView>(R.id.windSpeedText).visibility = View.GONE
        findViewById<ImageView>(R.id.weatherImage).visibility = View.GONE
    }
    private fun showViews() {
        findViewById<TextView>(R.id.city).visibility = View.VISIBLE
        findViewById<TextView>(R.id.state).visibility = View.VISIBLE
        findViewById<TextView>(R.id.temperature).visibility = View.VISIBLE
        findViewById<TextView>(R.id.pressureValueText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.humidityValueText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.windSpeedValueText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.pressureText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.humidityText).visibility = View.VISIBLE
        findViewById<TextView>(R.id.windSpeedText).visibility = View.VISIBLE
        findViewById<ImageView>(R.id.weatherImage).visibility = View.VISIBLE
    }

    private fun showProgressBarAndLoadingText(){
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        findViewById<TextView>(R.id.loadingTextView).visibility = View.VISIBLE
    }
    private fun hideProgressBarAndLoadingText(){
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        findViewById<TextView>(R.id.loadingTextView).visibility = View.GONE
    }
}


