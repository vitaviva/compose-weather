package com.example.androiddevchallenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val curDate: LocalDate = LocalDate.now()


val hourlyWeather = listOf(
    listOf(
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
    ),
    listOf(
        HourlyWeather(16, Weather.Cloud),
        HourlyWeather(18, Weather.Cloud),
        HourlyWeather(19, Weather.Sunnyrain),
        HourlyWeather(20, Weather.Sunnyrain),
        HourlyWeather(13, Weather.Sunnyrain),
        HourlyWeather(24, Weather.Sunny),
        HourlyWeather(13, Weather.Cloud),
        HourlyWeather(14, Weather.Cloud),
        HourlyWeather(15, Weather.Sunnyrain),
        HourlyWeather(14, Weather.Sunnyrain),
        HourlyWeather(12, Weather.Sunnyrain),
        HourlyWeather(16, Weather.Sunny),
        HourlyWeather(14, Weather.Sunny),
        HourlyWeather(13, Weather.Sunny),
        HourlyWeather(16, Weather.Sunnyrain),
        HourlyWeather(21, Weather.Sunnyrain),
        HourlyWeather(10, Weather.Sunnyrain),
        HourlyWeather(20, Weather.Sunny),
        HourlyWeather(14, Weather.Cloud),
        HourlyWeather(18, Weather.Cloud),
        HourlyWeather(18, Weather.Sunnyrain),
        HourlyWeather(16, Weather.Sunnyrain),
        HourlyWeather(11, Weather.Sunny),
        HourlyWeather(16, Weather.Sunny),
    ),
)


@RequiresApi(Build.VERSION_CODES.O)
val dailyWeather =
    (0..6).map {
        DailyWeather(
            curDate.plusDays(it.toLong()),
            hourlyWeather[it % 2],
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

