package com.example.androidweatherapp

import com.google.gson.annotations.SerializedName

class Post {
    private var temp: Double = 0.0
    @SerializedName("feels_like")
    private var feelsLike: Double = 0.0
    @SerializedName("temp_min")
    private var tempMin: Double = 0.0
    @SerializedName("temp_max")
    private var tempMax: Double = 0.0
    private var pressure: Int = 0
    private var humidity: Int = 0
    private var country: String = ""
    @SerializedName("name")
    private var cityName: String = ""

    fun getTemp():Double = temp //here
    fun getFeelsLike():Double = feelsLike//here
    fun getTempMin():Double = tempMin
    fun getTempMax():Double = tempMax
    fun getPressure():Int = pressure//here
    fun getHumidity():Int = humidity//here
    fun getCountry():String = country//here
    fun getCityName():String = cityName //here

}