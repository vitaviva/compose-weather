package com.example.androiddevchallenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val curDate: LocalDate = LocalDate.now()


val hourlyWeather = listOf(
    HourlyWeather(15, Weather.Sunny),
    HourlyWeather(13, Weather.Sunny),
    HourlyWeather(18, Weather.Sunnyrain),
    HourlyWeather(20, Weather.Sunnyrain),
    HourlyWeather(19, Weather.Sunnyrain),
    HourlyWeather(20, Weather.Sunny),
    HourlyWeather(15, Weather.Cloud),
    HourlyWeather(19, Weather.Cloud),
    HourlyWeather(17, Weather.Sunnyrain),
    HourlyWeather(15, Weather.Sunnyrain),
    HourlyWeather(10, Weather.Sunny),
    HourlyWeather(15, Weather.Sunny),
    HourlyWeather(15, Weather.Sunny),
    HourlyWeather(13, Weather.Sunny),
    HourlyWeather(18, Weather.Sunnyrain),
    HourlyWeather(20, Weather.Sunnyrain),
    HourlyWeather(19, Weather.Sunnyrain),
    HourlyWeather(20, Weather.Sunny),
    HourlyWeather(15, Weather.Cloud),
    HourlyWeather(19, Weather.Cloud),
    HourlyWeather(17, Weather.Sunnyrain),
    HourlyWeather(15, Weather.Sunnyrain),
    HourlyWeather(10, Weather.Sunny),
    HourlyWeather(15, Weather.Sunny),
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

