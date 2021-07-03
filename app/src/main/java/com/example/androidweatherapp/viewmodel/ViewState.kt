package com.example.androidweatherapp.viewmodel

import android.view.View

data class ViewState(

    var isLoadingDialog: Boolean = false,

    var title: String = "",

    var cityTitle: String = "",

    var windSpeed: String = "",

    var humidity: String = "",

    var airPressure: String = "",

    var theTemp: String = "",

    var onFailureMessage: String = "",

    var failedResponseMessage: String = "",

    var viewOfText: Int = View.GONE
)