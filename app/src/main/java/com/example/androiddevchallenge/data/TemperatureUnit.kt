package com.example.androiddevchallenge.data

import androidx.compose.runtime.compositionLocalOf


enum class TemperatureUnit(val text: String) {
    Fahrenheit("℉"), Centigrade("℃")
}

val LocalTemUnit = compositionLocalOf<TemperatureUnit> { error("No data found!") }

