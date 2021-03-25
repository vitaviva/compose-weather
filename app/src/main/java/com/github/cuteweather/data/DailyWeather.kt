/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cuteweather.data

import android.os.Build
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

data class DailyWeather(
    val isToday: Boolean = false,
    val date: DateToDisplay,
    val hourly: List<HourlyWeather>,
    @Deprecated("use weather() replace", replaceWith = ReplaceWith("weather()"))
    val weather: Weather
)

// display name of temperature
fun Int.displayName(temperatureUnit: TemperatureUnit) =
    if (temperatureUnit == TemperatureUnit.Centigrade) this.toString()
    else (this * 1.8f + 32).roundToInt().toString()

/**
 * if is today -> cur hourly weather
 * else -> average weather
 */
fun DailyWeather.weather() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (isToday) hourly[LocalTime.now().hour / 2].weather
        else weather
    } else { // meaningless data in order to be compiled for low sdk version
        hourly[0].weather
    }

fun DailyWeather.temperature() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (isToday) hourly[LocalTime.now().hour / 2].temperature
        else averageTemperature
    } else {
        0
    }

val DailyWeather.temperatureRange
    get() = hourly.maxOf { it.temperature } to hourly.minOf { it.temperature }

val DailyWeather.averageTemperature
    get() = hourly.sumBy { it.temperature } / hourly.size

class DateToDisplay(private val _date: LocalDate? = null) {
    val dayOfWeek
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireNotNull(_date).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)
        } else {
            MinSdkWarning
        }
    val dayOfWeekFull
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireNotNull(_date).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)
        } else {
            MinSdkWarning
        }
    val dayOfMonth
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            "${requireNotNull(_date).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)}, ${
            _date.month.getDisplayName(
                TextStyle.SHORT,
                Locale.US
            )
            } ${_date.dayOfMonth}"
        } else {
            MinSdkWarning
        }
}

private const val MinSdkWarning = "Min sdk version is 26"
