package com.example.androiddevchallenge

import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
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
import kotlin.math.abs


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
        LazyRow(
            Modifier
                .height(200.dp)
                .wrapContentWidth()
        ) {
            item {
                LineChart(
                    Modifier
                        .fillMaxHeight()
                        .width(900.dp)
                )
            }

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
                        text = when (it) {
                            0 -> "Today"
                            1 -> "Tomorrow"
                            else -> dailyWeather[it].dayOfWeek()
                        },
                        style = TextStyle(fontSize = 10.sp),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        dailyWeather[it].weather.icon()
                    }

                }

            }

        }

    }
}


@Composable
fun LineChart(modifier: Modifier) {

    Canvas(modifier) {

        val increament = size.width / 12
        val step = size.height / (dailyWeather[0].hourly.maxOf { it.temperature } * 1.2f)

        drawIntoCanvas {

            val path = Path()

            val points = dailyWeather[0].hourly.mapIndexed { index, hourlyWeather ->
                Offset(
                    increament * index + increament / 2,
                    size.height - hourlyWeather.temperature * step
                )
            }

            it.drawPoints(PointMode.Points, points, androidx.compose.ui.graphics.Paint().apply {
                strokeWidth = 20f
                strokeCap = StrokeCap.Round
                color = Color.Black
            })


            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)


//            val xMoveDisptance = increament / 4
//            val yMoveDistancce = step / 4

            points.take(11).forEachIndexed { index, it ->

                val startP = it
                val endP = points[index + 1]

//                val controlX0 = (startP.x + cx) / 2
//                val controlY0 = (startP.y + cy) / 2
//                val controlX1 = (endP.x + cx) / 2
//                val controlY1 = (endP.y + cy) / 2
//
//                if (endP.y < startP.y) {
//                    val p2 = Offset(controlX0 + xMoveDisptance, controlY0 - yMoveDistancce)
//                    val p3 = Offset(controlX1 - xMoveDisptance, controlY1 + yMoveDistancce)
//                    path.cubicTo(p2.x, p2.y, p3.x, p3.y, endP.x, endP.y)
//                } else {
//                    val p2 = Offset(controlX0 + xMoveDisptance, controlY0 + xMoveDisptance)
//                    val p3 = Offset(controlX1 - yMoveDistancce, controlY0 - yMoveDistancce)
//                    path.cubicTo(p2.x, p2.y, p3.x, p3.y, endP.x, endP.y)
//                }

                val p2: Offset
                val p3: Offset
                val cx = (startP.x + endP.x) / 2
                val dy = abs((endP.y - startP.y) / 4)
                if (endP.y < startP.y) {
                    p2 = Offset(cx, startP.y - dy)
                    p3 = Offset(cx, endP.y + dy)
                } else {
                    p2 = Offset(cx, startP.y + dy)
                    p3 = Offset(cx, endP.y - dy)
                }
                path.cubicTo(p2.x, p2.y, p3.x, p3.y, endP.x, endP.y)

            }

            path.lineTo(points.last().x + increament / 2, points.last().y)
            path.lineTo(points.last().x + increament / 2, size.height)
            path.lineTo(0f, size.height)
            path.lineTo(0f, points.first().y)


            it.nativeCanvas.drawPath(path, Paint().apply {
                val linearGradient = LinearGradient(
                    0f, 0f,
                    0f,
                    500f,
//                    android.graphics.Color.argb(255, 229, 160, 144),
                    Color.Black.copy(alpha = 0.1f).toArgb(),
                    Color.Transparent.toArgb(),
                    Shader.TileMode.CLAMP
                )

                shader = linearGradient
                style = Paint.Style.FILL
                isAntiAlias = true
            })


        }
    }

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