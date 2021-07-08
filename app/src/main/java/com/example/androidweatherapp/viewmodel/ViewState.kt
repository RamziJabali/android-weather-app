package com.example.androidweatherapp.viewmodel

import android.view.View
import java.io.SyncFailedException

data class ViewState(

    var isLoadingDialog: Boolean = false,

    var didCallFail: Boolean = false,

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