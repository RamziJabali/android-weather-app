package com.example.androidweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

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
        val response = call.execute()
        val weatherForLocation = response.body()
        if (weatherForLocation != null) {
            //Toast.makeText(this, "We have a response", Toast.LENGTH_SHORT).show()
            findViewById<TextView>(R.id.city).setText(weatherForLocation.cityTitle)
            findViewById<TextView>(R.id.state).setText(weatherForLocation.parentRegion.title)
        }
    }
}