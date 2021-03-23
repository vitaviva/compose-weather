package com.example.androiddevchallenge.data

import android.annotation.SuppressLint
import androidx.compose.ui.util.fastSumBy
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*
import kotlin.math.roundToInt

data class DailyWeather(
    val date: LocalDate,
    val hourly: List<HourlyWeather>,
    val weather: Weather
)

data class HourlyWeather(
    val temperature: Int, val weather: Weather
)

// display name of temperature
fun Int.displayName(temperatureUnit: TemperatureUnit) =
    if (temperatureUnit == TemperatureUnit.Centigrade) this.toString()
    else (this * 1.8f + 32).roundToInt().toString()


val DailyWeather.dayOfMonth
    @SuppressLint("NewApi")
    get() = "${date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)}, ${
        date.month.getDisplayName(
            TextStyle.SHORT,
            Locale.US
        )
    } ${date.dayOfMonth}"


val DailyWeather.dayOfWeek
    @SuppressLint("NewApi")
    get() = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)

val DailyWeather.curHourlyWeather
    @SuppressLint("NewApi")
    get() = hourly[LocalTime.now().hour / 2]


val DailyWeather.temperatureRange
    get() = hourly.maxOf { it.temperature } to hourly.minOf { it.temperature }


val DailyWeather.averageTemperature
    get() = hourly.fastSumBy { it.temperature } / hourly.size
