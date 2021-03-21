package com.example.androiddevchallenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val curDate: LocalDate = LocalDate.now()


val hourlyWeather = listOf(
    HourlyWeather(15f, Weather.Sunny),
    HourlyWeather(13f, Weather.Sunny),
    HourlyWeather(18f, Weather.Sunnyrain),
    HourlyWeather(20f, Weather.Sunnyrain),
    HourlyWeather(19f, Weather.Sunnyrain),
    HourlyWeather(20f, Weather.Sunny),
    HourlyWeather(15f, Weather.Cloud),
    HourlyWeather(19f, Weather.Cloud),
    HourlyWeather(17f, Weather.Sunnyrain),
    HourlyWeather(15f, Weather.Sunnyrain),
    HourlyWeather(10f, Weather.Sunny),
    HourlyWeather(15f, Weather.Sunny),
)

@RequiresApi(Build.VERSION_CODES.O)
val dailyWeather =
    (0..6).map {
        DailyWeather(
            curDate.plusDays(it.toLong()),
            hourlyWeather,
            when (it) {
                0 -> Weather.Sunny
                1 -> Weather.Sunny
                2 -> Weather.Cloud
                3 -> Weather.Cloud
                4 -> Weather.Cloud
                5 -> Weather.Sunny
                else -> Weather.Sunny
            }
        )
    }.toList()

