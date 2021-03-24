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
import java.time.LocalTime
import kotlin.math.roundToInt

data class DailyWeather(
    val dayOfWeek: String = MinSdkWarning,
    val dayOfMonth: String = MinSdkWarning,
    val hourly: List<HourlyWeather>,
    val weather: Weather
)

data class HourlyWeather(
    val temperature: Int,
    val weather: Weather
)

// display name of temperature
fun Int.displayName(temperatureUnit: TemperatureUnit) =
    if (temperatureUnit == TemperatureUnit.Centigrade) this.toString()
    else (this * 1.8f + 32).roundToInt().toString()

val DailyWeather.curHourlyWeather
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        hourly[LocalTime.now().hour / 2]
    } else {
        hourly[0]
    }

val DailyWeather.temperatureRange
    get() = hourly.maxOf { it.temperature } to hourly.minOf { it.temperature }

val DailyWeather.averageTemperature
    get() = hourly.sumBy { it.temperature } / hourly.size

private const val MinSdkWarning = "Min sdk version is 26"
