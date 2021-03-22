package com.example.androiddevchallenge

import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.graphics.Typeface
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
import com.example.androiddevchallenge.data.DailyWeather
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.averageTemperature
import com.example.androiddevchallenge.data.curHourlyWeather
import com.example.androiddevchallenge.data.dailyWeather
import com.example.androiddevchallenge.data.dayOfMonth
import com.example.androiddevchallenge.data.dayOfWeek
import com.example.androiddevchallenge.data.temperatureRange
import com.example.androiddevchallenge.ui.Pager
import com.example.androiddevchallenge.ui.PagerState
import kotlin.math.abs


@Preview
@Composable
fun WeatherView() {

    val pagerState = remember { PagerState() }
    var selectedIndex by remember { mutableStateOf(0) }
    val (selectedDay, curWeather) = remember(selectedIndex) {
        val day = dailyWeather[selectedIndex]
        day to if (selectedIndex == 0) day.curHourlyWeather.weather
        else day.weather
    }
    DisposableEffect(Unit) {
        pagerState.maxPage = (dailyWeather.size - 1).coerceAtLeast(0)
        onDispose { }
    }
    DisposableEffect(pagerState.selectionState) {
        //whenever pager changed，get latest index
        selectedIndex = pagerState.currentPage
        onDispose { }
    }
    DisposableEffect(selectedIndex) {
        pagerState.currentPage = selectedIndex
        onDispose { }
    }

    val color1 by backgroundColorState(curWeather.background.first)
    val color2 by backgroundColorState(curWeather.background.second)
    val color3 by backgroundColorState(curWeather.background.third)

    Column(
        modifier = Modifier
            .width(800.dp)
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        color1,
                        color2,
                        color3
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
                curWeather
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        DraggableState { }
        Pager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {

            val day = dailyWeather[page]

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.align(Alignment.TopCenter)) {

                    Text(
                        day.dayOfMonth,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        day.weather.text,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Normal,
                        ), modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(100.dp)
                    ) {

                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Text(
                                "${day.temperatureRange.first}↑",
                                style = TextStyle(
                                    fontSize = 19.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                "${day.temperatureRange.second}↓",
                                style = TextStyle(
                                    fontSize = 19.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                        }


                        Text(
                            "${day.averageTemperature}",
                            style = TextStyle(
                                fontSize = 70.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold
                            ),
                            lineHeight = 0.sp,
                            letterSpacing = 0.sp
                        )
                        Text(
                            "℃",
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.SemiBold
                            ), modifier = Modifier.padding(top = 10.dp)
                        )
                    }


                }

            }


        }

        HourlyWeatherChart(dailyWeather = selectedDay)

        Divider(
            Modifier
                .padding(top = 10.dp)
                .height(0.5.dp)
        )

        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            (dailyWeather.indices).forEachIndexed { index, it ->
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            selectedIndex = it
                        }) {
                    Column(
                        Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = when (it) {
                                0 -> "Today"
                                1 -> "Tomorrow"
                                else -> dailyWeather[it].dayOfWeek
                            },
                            style = TextStyle(fontSize = 10.sp),
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


                if (index < 6)
                    Divider(
                        Modifier
                            .height(70.dp)
                            .width(0.5.dp)
                            .align(Alignment.CenterVertically)
                    )

            }

        }

    }
}


/**
 * Hourly weather chart
 * @param [dailyWeather] rendered daily weather
 */
@Composable
fun HourlyWeatherChart(
    modifier: Modifier = Modifier,
    dailyWeather: DailyWeather
) {
    LazyRow(
        modifier
            .height(100.dp)
            .wrapContentWidth()
    ) {
        item {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(900.dp)
            ) {

                Row(Modifier.fillMaxSize()) {
                    dailyWeather.hourly.forEachIndexed { index, it ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .alpha(0.6f)
                                .align(Alignment.Bottom)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 6.dp, end = 6.dp)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                it.weather.icon()
                            }
                            Text(
                                if (index < 12) "${(index + 1)} AM"
                                else "${(index - 11)} PM",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                }

                LineChart(Modifier.fillMaxSize(), dailyWeather)

            }

        }

    }
}



/**
 * Render hourly temperature with curve-line chart
 * show with animation
 */
@Composable
fun LineChart(modifier: Modifier, dailyWeather: DailyWeather) {

    val (cur, setCur) = remember { mutableStateOf<DailyWeather?>(null) }

    var trigger by remember { mutableStateOf(0f) }

    DisposableEffect(dailyWeather) {
        trigger = 1f
        onDispose { }
    }

    val animateFloat by animateFloatAsState(
        targetValue = trigger,
        animationSpec = tween(1500)
    ) {
        setCur(dailyWeather)
        trigger = 0f
    }

    Canvas(modifier) {

        val increament = size.width / dailyWeather.hourly.size
        val step = size.height / (dailyWeather.hourly.maxOf { it.temperature } * 4f)

        drawIntoCanvas { canvas ->

            if (cur != dailyWeather) { // change visible range according to animation
                canvas.clipRect(Rect(0f, 0f, size.width * animateFloat, size.height))
            }

            val path = Path()

            val points = dailyWeather.hourly.mapIndexed { index, hourlyWeather ->
                Offset(
                    increament * index + increament / 2,
                    size.height * 0.5f - hourlyWeather.temperature * step
                )
            }


            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)


            points.take(points.size - 1).forEachIndexed { index, it ->

                val startP = it
                val endP = points[index + 1]

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

            //drawPath
            canvas.nativeCanvas.drawPath(path, Paint().apply {
                val linearGradient = LinearGradient(
                    0f, 0f,
                    0f,
                    200f,
//                    android.graphics.Color.argb(255, 229, 160, 144),
                    Color.Black.copy(alpha = 0.1f).toArgb(),
                    Color.Transparent.toArgb(),
                    Shader.TileMode.CLAMP
                )

                shader = linearGradient
                style = Paint.Style.FILL
                isAntiAlias = true
            })

            //draw Points
            canvas.drawPoints(PointMode.Points, points, androidx.compose.ui.graphics.Paint().apply {
                strokeWidth = 8f
                strokeCap = StrokeCap.Round
                color = Color.Black.copy(0.6f)
            })

            //drawText
            val size = 10.sp.toPx()
            val textPaint = Paint().apply {
                color = Color.Black.toArgb()
                textSize = size
                alpha = 90
                typeface = Typeface.MONOSPACE

            }
            dailyWeather.hourly.asSequence().zip(points.asSequence())
                .forEachIndexed { index, pair ->
                    val (weather, points) = pair
                    canvas.nativeCanvas.drawText(
                        "${weather.temperature}",
                        points.x - size / 2,
                        points.y - size / 1.5f,
                        textPaint
                    )
                }


        }
    }

}


@Composable
fun WeatherIcon(modifier: Modifier, weatherIcon: Weather) {

    val (cur, setCur) = remember { mutableStateOf(weatherIcon) }
    var trigger by remember { mutableStateOf(0f) }

    DisposableEffect(weatherIcon.animatable) {
        trigger = 1f
        onDispose { }
    }

    val animateFloat by animateFloatAsState(
        targetValue = trigger,
        animationSpec = tween(1000)
    ) {
        setCur(weatherIcon)
        trigger = 0f
    }


    val composeInfo = remember(animateFloat) {
        cur.animatable + (weatherIcon.animatable - cur.animatable) * animateFloat
    }

    ComposedIcon(
        modifier,
        composeInfo
    )
}

@Composable
fun backgroundColorState(target: Color) =
    animateColorAsState(
        targetValue = target,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
