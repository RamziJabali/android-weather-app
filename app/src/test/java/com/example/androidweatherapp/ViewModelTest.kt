package com.example.androidweatherapp

import com.example.androidweatherapp.viewmodel.ViewModel
import com.example.androidweatherapp.views.MainActivity
import org.junit.Before
import org.junit.Test
import io.mockk.*
import org.junit.Assert.*

class ViewModelTest {

    lateinit var mainActivity: MainActivity
    lateinit var viewModel: ViewModel

    @Before
    fun setUp() {
        mainActivity = mockk(relaxed = true)
        viewModel = ViewModel(mainActivity)
        viewModel.startApp()
    }

    @Test
    fun isStateTextViewPopulated() {
        assertEquals(true, mainActivity.stateTextView.toString().isNotBlank())
    }

    @Test
    fun isCityTextViewPopulated() {
        assertEquals(true, mainActivity.cityTextView.toString().isNotBlank())
    }

    @Test
    fun isWindSpeedTextViewPopulated() {
        assertEquals(true, mainActivity.windSpeedValueTextView.toString().isNotBlank())
    }

    @Test
    fun isHumidityTextViewPopulated() {
        assertEquals(true, mainActivity.humidityValueTextView.toString().isNotBlank())
    }

    @Test
    fun isWindPressureTextViewPopulated() {
        assertEquals(true, mainActivity.pressureValueTextView.toString().isNotBlank())
    }
}