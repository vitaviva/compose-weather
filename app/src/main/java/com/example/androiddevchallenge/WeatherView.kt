package com.example.androiddevchallenge

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.curHourlyWeather
import com.example.androiddevchallenge.data.dailyWeather
import com.example.androiddevchallenge.data.dayOfWeek


@Preview
@Composable
fun WeatherView() {

    var weather by remember { mutableStateOf(dailyWeather[0]) }

    Column(
        modifier = Modifier
            .width(800.dp)
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color.LightGray,
                        Color.Gray,
                        Color.White
                    )
                )
            )
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            WeatherIcon(
                Modifier.align(Alignment.Center),
                weather.curHourlyWeather().weather
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                "36C", style = TextStyle(
                    fontSize = 80.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Row(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.4f))
        ) {
            Text("am pm am")
        }

        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            (0..6).forEach {
                Column(
                    Modifier
                        .weight(1f)
                        .clickable {
                            weather = dailyWeather[it]
                        }
                ) {
                    Text(
                        text = dailyWeather[it].dayOfWeek(),
                        style = TextStyle(fontSize = 20.sp),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Box(modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)) {
                        dailyWeather[it].weather.icon()
                    }

                }

            }

        }

    }
}


@Composable
fun LineChart() {

}


@Composable
fun WeatherIcon(modifier: Modifier, weatherIcon: Weather) {

    val (cur, setCur) = remember { mutableStateOf(weatherIcon) }
    var trigger by remember { mutableStateOf(0f) }

    DisposableEffect(weatherIcon.animatableIcon) {
        trigger = 1f
        onDispose { }
    }

    val animateFloat by animateFloatAsState(
        targetValue = trigger,
        finishedListener = {
            setCur(weatherIcon)
            trigger = 0f
        }, animationSpec = tween(1000)
    )

    val composeInfo = remember(animateFloat) {
        cur.animatableIcon + (weatherIcon.animatableIcon - cur.animatableIcon) * animateFloat
    }

    ComposedIcon(
        modifier,
        composeInfo
    )
}