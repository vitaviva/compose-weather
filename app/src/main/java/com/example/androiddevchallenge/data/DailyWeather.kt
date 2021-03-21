package com.example.androiddevchallenge.data

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*

data class DailyWeather(
    val date: LocalDate,
    val hourly: List<HourlyWeather>,
    val weather: Weather
)

data class HourlyWeather(
    val temperature: Float, val weather: Weather
)


@SuppressLint("NewApi")
fun DailyWeather.dayOfWeek() = run {
    date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)
}

@SuppressLint("NewApi")
fun DailyWeather.curHourlyWeather() = run {
    hourly[LocalTime.now().hour / 2]
}
