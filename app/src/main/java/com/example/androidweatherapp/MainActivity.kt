package com.example.androidweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidweatherapp.models.Location
import com.example.androidweatherapp.models.WeatherForLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }

    lateinit var api: JsonWeatherApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = ApiServiceBuilder(JsonWeatherApi::class.java)
            .withApiBaseUrl(API_BASE_URL)
            .build()

        var loadingDialog = LoadingDialog(this)

        getCountryAndCityName(2487956)
//        getCities()
    }

    //44418
    private fun getCountryAndCityName(whereOnEarthId: Int) {
        val call = api.getWeatherForWhereOnEarthId(whereOnEarthId)
        call.enqueue(
            object : Callback<WeatherForLocation> {
                override fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
                    findViewById<TextView>(R.id.city).text = t.message
                    findViewById<TextView>(R.id.state).text = t.message
                    findViewById<TextView>(R.id.temperature).text = t.message
                }

                override fun onResponse(
                    call: Call<WeatherForLocation>,
                    response: Response<WeatherForLocation>
                ) {
                    if (!response.isSuccessful) {
                        findViewById<TextView>(R.id.city).text = "Code: " + response.code()
                        findViewById<TextView>(R.id.state).text = "Code: " + response.code()
                        return
                    }
                    val weatherForLocation = response.body()
                    findViewById<TextView>(R.id.city).text = weatherForLocation!!.cityTitle
                    findViewById<TextView>(R.id.state).text = weatherForLocation.parentRegion.title
                    findViewById<TextView>(R.id.temperature).text = weatherForLocation.consolidatedWeather[0].theTemp.toString() + "C"
                    findViewById<TextView>(R.id.pressureValueText).text = weatherForLocation.consolidatedWeather[0].airPressure.toString()
                    findViewById<TextView>(R.id.humidityValueText).text = weatherForLocation.consolidatedWeather[0].humidity.toString()
                    findViewById<TextView>(R.id.windSpeedValueText).text = weatherForLocation.consolidatedWeather[0].windSpeed.toString()
                }
            })
    }

    //San%20Francisco, 2487956
    private fun getCities() {
        val call = api.getCitiesForQuery("london") //error here
        call.enqueue(
            object : Callback<List<Location>> {
//                override fun onFailure(call: Call<Location>, t: Throwable) {
//                    findViewById<TextView>(R.id.city).text = t.message
//                    findViewById<TextView>(R.id.state).text = t.message
//                }
//
//                override fun onResponse(call: Call<Location>, response: Response<Location>) {
//                    if (!response.isSuccessful) {
//                        findViewById<TextView>(R.id.city).text = "Code: " + response.code()
//                        findViewById<TextView>(R.id.state).text = "Code: " + response.code()
//                        return
//                    }
//                    val location = response.body()
//                    findViewById<TextView>(R.id.city).text = location!!.cityTitle
//                    findViewById<TextView>(R.id.state).text = location.location_type
//                }

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
                    findViewById<TextView>(R.id.city).text =  locationOfCity!![0].cityTitle
                    findViewById<TextView>(R.id.state).text = locationOfCity[0].location_type
                }

            })
    }
}


