package com.example.androiddevchallenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val curDate: LocalDate = LocalDate.now()


val hourlyWeather = listOf(
    HourlyWeather(3f, Weather.Sunny),
    HourlyWeather(5f, Weather.Sunny),
    HourlyWeather(7f, Weather.Sunnyrain),
    HourlyWeather(5f, Weather.Sunnyrain),
    HourlyWeather(7f, Weather.Sunnyrain),
    HourlyWeather(9f, Weather.Sunny),
    HourlyWeather(3f, Weather.Cloud),
    HourlyWeather(5f, Weather.Cloud),
    HourlyWeather(7f, Weather.Sunnyrain),
    HourlyWeather(5f, Weather.Sunnyrain),
    HourlyWeather(7f, Weather.Sunny),
    HourlyWeather(9f, Weather.Sunny),
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

