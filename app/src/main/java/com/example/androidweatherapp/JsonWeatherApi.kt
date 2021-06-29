package com.example.androidweatherapp

import retrofit2.Call
import retrofit2.http.GET

interface JsonWeatherApi {

    @GET("")
    fun getPosts():Call<List<Post>>
}