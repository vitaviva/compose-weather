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

/**
 * note：temperature data use "℃" as default
 */
private val hourlyWeather = listOf(
    listOf(
        HourlyWeather(5, Weather.Sunny),
        HourlyWeather(6, Weather.Sunny),
        HourlyWeather(6, Weather.Sunny),
        HourlyWeather(7, Weather.Sunny),
        HourlyWeather(9, Weather.Sunny),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(11, Weather.Sunny),
        HourlyWeather(12, Weather.Sunny),
        HourlyWeather(14, Weather.MostlyClear),
        HourlyWeather(15, Weather.MostlyClear),
        HourlyWeather(16, Weather.Sunny),
        HourlyWeather(18, Weather.Sunny),
        HourlyWeather(17, Weather.Sunny),
        HourlyWeather(15, Weather.Sunny),
        HourlyWeather(13, Weather.MostlyClear),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(8, Weather.Sunny),
        HourlyWeather(7, Weather.Sunny),
        HourlyWeather(6, Weather.Sunny),
        HourlyWeather(5, Weather.Sunny),
        HourlyWeather(4, Weather.Sunny),
        HourlyWeather(4, Weather.MostlyClear),
        HourlyWeather(3, Weather.MostlyClear),
    ),
    listOf(
        HourlyWeather(6, Weather.Sunny),
        HourlyWeather(8, Weather.MostlyClear),
        HourlyWeather(9, Weather.MostlyClear),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(12, Weather.MostlyClear),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(17, Weather.MostlyClear),
        HourlyWeather(19, Weather.MostlyClear),
        HourlyWeather(20, Weather.Cloudy),
        HourlyWeather(21, Weather.Cloudy),
        HourlyWeather(21, Weather.Cloudy),
        HourlyWeather(22, Weather.Cloudy),
        HourlyWeather(20, Weather.MostlyClear),
        HourlyWeather(19, Weather.MostlyClear),
        HourlyWeather(18, Weather.MostlyClear),
        HourlyWeather(16, Weather.MostlyClear),
        HourlyWeather(15, Weather.MostlyClear),
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(6, Weather.Cloudy),
        HourlyWeather(5, Weather.MostlyClear),
        HourlyWeather(7, Weather.MostlyClear),
    ),
    listOf(
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(19, Weather.Cloudy),
        HourlyWeather(20, Weather.Cloudy),
        HourlyWeather(23, Weather.Cloudy),
        HourlyWeather(24, Weather.Cloudy),
        HourlyWeather(25, Weather.MostlyClear),
        HourlyWeather(28, Weather.MostlyClear),
        HourlyWeather(32, Weather.MostlyClear),
        HourlyWeather(29, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(17, Weather.Cloudy),
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(16, Weather.MostlyClear),
        HourlyWeather(14, Weather.MostlyClear),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(8, Weather.MostlyClear),
        HourlyWeather(6, Weather.Cloudy),
        HourlyWeather(6, Weather.Cloudy),
        HourlyWeather(5, Weather.MostlyClear),
    ),
    listOf(
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(11, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(14, Weather.CloudyRain),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(16, Weather.CloudyRain),
        HourlyWeather(17, Weather.Cloudy),
        HourlyWeather(18, Weather.CloudyRain),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(19, Weather.CloudyRain),
        HourlyWeather(22, Weather.Cloudy),
        HourlyWeather(23, Weather.CloudyRain),
        HourlyWeather(22, Weather.Cloudy),
        HourlyWeather(20, Weather.Cloudy),
        HourlyWeather(19, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(17, Weather.CloudyRain),
        HourlyWeather(15, Weather.CloudyRain),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(11, Weather.CloudyRain),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(9, Weather.Cloudy),

    ),
    listOf(
        HourlyWeather(8, Weather.Cloudy),
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(14, Weather.CloudyRain),
        HourlyWeather(14, Weather.CloudyRain),
        HourlyWeather(15, Weather.CloudyRain),
        HourlyWeather(16, Weather.HeavyRain),
        HourlyWeather(18, Weather.HeavyRain),
        HourlyWeather(18, Weather.HeavyRain),
        HourlyWeather(19, Weather.HeavyRain),
        HourlyWeather(21, Weather.CloudyRain),
        HourlyWeather(23, Weather.CloudyRain),
        HourlyWeather(22, Weather.Cloudy),
        HourlyWeather(20, Weather.Cloudy),
        HourlyWeather(19, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(19, Weather.CloudyRain),
        HourlyWeather(18, Weather.CloudyRain),
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(11, Weather.CloudyRain),
        HourlyWeather(10, Weather.Cloudy),
    ),
    listOf(
        HourlyWeather(8, Weather.MostlyClear),
        HourlyWeather(10, Weather.MostlyClear),
        HourlyWeather(11, Weather.MostlyClear),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(20, Weather.Cloudy),
        HourlyWeather(23, Weather.Cloudy),
        HourlyWeather(25, Weather.Cloudy),
        HourlyWeather(28, Weather.MostlyClear),
        HourlyWeather(27, Weather.MostlyClear),
        HourlyWeather(26, Weather.MostlyClear),
        HourlyWeather(25, Weather.Cloudy),
        HourlyWeather(24, Weather.Cloudy),
        HourlyWeather(22, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(16, Weather.MostlyClear),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(12, Weather.MostlyClear),
        HourlyWeather(11, Weather.MostlyClear),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(7, Weather.Cloudy),
    ),
    listOf(
        HourlyWeather(10, Weather.Sunny),
        HourlyWeather(11, Weather.Sunny),
        HourlyWeather(12, Weather.Sunny),
        HourlyWeather(14, Weather.MostlyClear),
        HourlyWeather(18, Weather.MostlyClear),
        HourlyWeather(19, Weather.Sunny),
        HourlyWeather(21, Weather.Sunny),
        HourlyWeather(24, Weather.Sunny),
        HourlyWeather(27, Weather.Sunny),
        HourlyWeather(30, Weather.MostlyClear),
        HourlyWeather(32, Weather.MostlyClear),
        HourlyWeather(35, Weather.Sunny),
        HourlyWeather(33, Weather.Sunny),
        HourlyWeather(30, Weather.Sunny),
        HourlyWeather(26, Weather.Sunny),
        HourlyWeather(25, Weather.MostlyClear),
        HourlyWeather(24, Weather.MostlyClear),
        HourlyWeather(20, Weather.MostlyClear),
        HourlyWeather(18, Weather.Sunny),
        HourlyWeather(15, Weather.Sunny),
        HourlyWeather(14, Weather.Sunny),
        HourlyWeather(12, Weather.MostlyClear),
        HourlyWeather(11, Weather.Sunny),
        HourlyWeather(10, Weather.Sunny),
    ),
)

object WeatherDataProvider {

    // mock the average weather of day
    val _weather = { it: Int ->
        when (it) {
            0 -> Weather.Sunny
            1 -> Weather.MostlyClear
            2 -> Weather.Cloudy
            3 -> Weather.CloudyRain
            4 -> Weather.HeavyRain
            5 -> Weather.Cloudy
            else -> Weather.Sunny
        }
    }

    val dailyWeather =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val curDate: LocalDate = LocalDate.now()
            (0..6).map {
                val date = curDate.plusDays(it.toLong())
                DailyWeather(
                    isToday = it == 0,
                    DateToDisplay(date),
                    hourlyWeather[it],
                    _weather(it)
                )
            }.toList()
        } else (0..6).map {
            DailyWeather(
                date = DateToDisplay(),
                hourly = hourlyWeather[it],
                weather = _weather(it)
            )
        }.toList()
}
