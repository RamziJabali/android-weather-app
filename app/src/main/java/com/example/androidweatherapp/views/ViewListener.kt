package com.example.androidweatherapp.views

import com.example.androidweatherapp.viewmodel.ViewState

interface ViewListener {
    fun setNewViewState(viewState: ViewState)
}