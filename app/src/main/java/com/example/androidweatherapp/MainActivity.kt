package com.example.androidweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.androidweatherapp.models.WeatherForLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val api = ApiServiceBuilder(JsonWeatherApi::class.java)
            .withApiBaseUrl(API_BASE_URL)
            .build()

        val call = api.getWeatherForWhereOnEarthId(44418)



        call.enqueue(object : Callback<WeatherForLocation> {
            override fun onFailure(call: Call<WeatherForLocation>, t: Throwable) {
                findViewById<TextView>(R.id.city).text = t.message
                findViewById<TextView>(R.id.state).text = t.message
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
            }
        })
    }
}


