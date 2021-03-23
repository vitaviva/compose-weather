package com.example.androiddevchallenge.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


/**
 * note：temperature data use "℃" as default
 */
private val hourlyWeather = listOf(
    listOf(
        HourlyWeather(5, Weather.Clear),
        HourlyWeather(6, Weather.Clear),
        HourlyWeather(6, Weather.CloudyRains),
        HourlyWeather(7, Weather.CloudyRains),
        HourlyWeather(9, Weather.CloudyRains),
        HourlyWeather(10, Weather.Clear),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(11, Weather.Cloudy),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(15, Weather.Clear),
        HourlyWeather(16, Weather.Clear),
        HourlyWeather(18, Weather.Clear),
        HourlyWeather(17, Weather.Clear),
        HourlyWeather(15, Weather.CloudyRains),
        HourlyWeather(13, Weather.CloudyRains),
        HourlyWeather(10, Weather.CloudyRains),
        HourlyWeather(8, Weather.Clear),
        HourlyWeather(7, Weather.Cloudy),
        HourlyWeather(6, Weather.Cloudy),
        HourlyWeather(5, Weather.CloudyRains),
        HourlyWeather(4, Weather.CloudyRains),
        HourlyWeather(4, Weather.Clear),
        HourlyWeather(3, Weather.Clear),
    ),
    listOf(
        HourlyWeather(6, Weather.Clear),
        HourlyWeather(8, Weather.Clear),
        HourlyWeather(9, Weather.CloudyRains),
        HourlyWeather(10, Weather.CloudyRains),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(13, Weather.Clear),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(17, Weather.CloudyRains),
        HourlyWeather(19, Weather.CloudyRains),
        HourlyWeather(20, Weather.Clear),
        HourlyWeather(21, Weather.Clear),
        HourlyWeather(21, Weather.Clear),
        HourlyWeather(22, Weather.Clear),
        HourlyWeather(20, Weather.CloudyRains),
        HourlyWeather(19, Weather.CloudyRains),
        HourlyWeather(18, Weather.CloudyRains),
        HourlyWeather(16, Weather.Clear),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(10, Weather.CloudyRains),
        HourlyWeather(6, Weather.CloudyRains),
        HourlyWeather(5, Weather.Clear),
        HourlyWeather(7, Weather.Clear),
    ),
    listOf(
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(19, Weather.CloudyRains),
        HourlyWeather(20, Weather.CloudyRains),
        HourlyWeather(13, Weather.CloudyRains),
        HourlyWeather(24, Weather.Clear),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(15, Weather.CloudyRains),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(16, Weather.Clear),
        HourlyWeather(14, Weather.Clear),
        HourlyWeather(13, Weather.Clear),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(21, Weather.CloudyRains),
        HourlyWeather(10, Weather.CloudyRains),
        HourlyWeather(20, Weather.Clear),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(18, Weather.CloudyRains),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(11, Weather.Clear),
        HourlyWeather(16, Weather.Clear),
    ),
    listOf(
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(12, Weather.Cloudy),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(19, Weather.CloudyRains),
        HourlyWeather(20, Weather.Clear),
        HourlyWeather(23, Weather.Cloudy),
        HourlyWeather(24, Weather.Cloudy),
        HourlyWeather(25, Weather.CloudyRains),
        HourlyWeather(28, Weather.CloudyRains),
        HourlyWeather(32, Weather.CloudyRains),
        HourlyWeather(29, Weather.Clear),
        HourlyWeather(18, Weather.Clear),
        HourlyWeather(17, Weather.Clear),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(10, Weather.Clear),
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(9, Weather.Cloudy),
        HourlyWeather(8, Weather.CloudyRains),
        HourlyWeather(6, Weather.CloudyRains),
        HourlyWeather(6, Weather.Clear),
        HourlyWeather(5, Weather.Clear),
    ),
    listOf(
        HourlyWeather(8, Weather.Cloudy),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(11, Weather.CloudyRains),
        HourlyWeather(13, Weather.CloudyRains),
        HourlyWeather(13, Weather.CloudyRains),
        HourlyWeather(14, Weather.Clear),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(20, Weather.CloudyRains),
        HourlyWeather(23, Weather.CloudyRains),
        HourlyWeather(25, Weather.CloudyRains),
        HourlyWeather(25, Weather.Clear),
        HourlyWeather(26, Weather.Clear),
        HourlyWeather(25, Weather.Clear),
        HourlyWeather(24, Weather.CloudyRains),
        HourlyWeather(20, Weather.CloudyRains),
        HourlyWeather(18, Weather.CloudyRains),
        HourlyWeather(16, Weather.Clear),
        HourlyWeather(15, Weather.Cloudy),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(11, Weather.CloudyRains),
        HourlyWeather(9, Weather.Clear),
        HourlyWeather(7, Weather.Clear),
    ),
    listOf(
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(19, Weather.CloudyRains),
        HourlyWeather(20, Weather.CloudyRains),
        HourlyWeather(13, Weather.CloudyRains),
        HourlyWeather(24, Weather.Clear),
        HourlyWeather(13, Weather.Cloudy),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(15, Weather.CloudyRains),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(16, Weather.Clear),
        HourlyWeather(14, Weather.Clear),
        HourlyWeather(13, Weather.Clear),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(21, Weather.CloudyRains),
        HourlyWeather(10, Weather.CloudyRains),
        HourlyWeather(20, Weather.Clear),
        HourlyWeather(14, Weather.Cloudy),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(18, Weather.CloudyRains),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(11, Weather.Clear),
        HourlyWeather(16, Weather.Clear),
    ),
    listOf(
        HourlyWeather(8, Weather.Cloudy),
        HourlyWeather(10, Weather.Cloudy),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(16, Weather.CloudyRains),
        HourlyWeather(19, Weather.Clear),
        HourlyWeather(21, Weather.Cloudy),
        HourlyWeather(24, Weather.Cloudy),
        HourlyWeather(27, Weather.CloudyRains),
        HourlyWeather(30, Weather.CloudyRains),
        HourlyWeather(32, Weather.CloudyRains),
        HourlyWeather(29, Weather.Clear),
        HourlyWeather(29, Weather.Clear),
        HourlyWeather(27, Weather.Clear),
        HourlyWeather(26, Weather.CloudyRains),
        HourlyWeather(25, Weather.CloudyRains),
        HourlyWeather(22, Weather.CloudyRains),
        HourlyWeather(20, Weather.Clear),
        HourlyWeather(18, Weather.Cloudy),
        HourlyWeather(16, Weather.Cloudy),
        HourlyWeather(14, Weather.CloudyRains),
        HourlyWeather(12, Weather.CloudyRains),
        HourlyWeather(11, Weather.Clear),
        HourlyWeather(8, Weather.Clear),
    ),
)


object WeatherDataProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    val dailyWeather =
        (0..6).map {
            DailyWeather(
                curDate.plusDays(it.toLong()),
                hourlyWeather[it],
                when (it) {
                    0 -> Weather.Clear
                    1 -> Weather.Clear
                    2 -> Weather.Cloudy
                    3 -> Weather.CloudyRains
                    4 -> Weather.Cloudy
                    5 -> Weather.Clear
                    else -> Weather.Clear
                }
            )
        }.toList()


}


@RequiresApi(Build.VERSION_CODES.O)
private val curDate: LocalDate = LocalDate.now()

