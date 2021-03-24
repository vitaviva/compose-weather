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
package com.github.cuteweather

import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.cuteweather.data.DailyWeather
import com.github.cuteweather.data.LocalTemUnit
import com.github.cuteweather.data.TemperatureUnit
import com.github.cuteweather.data.Weather
import com.github.cuteweather.data.WeatherDataProvider
import com.github.cuteweather.data.averageTemperature
import com.github.cuteweather.data.displayName
import com.github.cuteweather.data.temperature
import com.github.cuteweather.data.temperatureRange
import com.github.cuteweather.data.weather
import com.github.cuteweather.ui.ComposedIcon
import com.github.cuteweather.ui.Pager
import com.github.cuteweather.ui.PagerState
import com.github.cuteweather.ui.ShowDialog
import com.github.cuteweather.ui.theme.FontType
import kotlin.math.abs

@Preview
@Composable
fun WeatherView() {

    val (tempUnit, setTempUnit) = remember { mutableStateOf(TemperatureUnit.Fahrenheit) } // F or C

    val pagerState = remember { PagerState() } // state used for pager

    var selectedIndex by remember { mutableStateOf(0) } // selected index of paager

    val (selectedDay, curWeather) = remember(selectedIndex) { // selectDay and current weather
        val day = WeatherDataProvider.dailyWeather[selectedIndex]
        day to day.weather()
    }

    DisposableEffect(Unit) {
        pagerState.maxPage = (WeatherDataProvider.dailyWeather.size - 1).coerceAtLeast(0)
        onDispose { }
    }
    DisposableEffect(pagerState.selectionState) {
        // whenever pager changed，get newest index
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

    CompositionLocalProvider(LocalTemUnit provides tempUnit) {
        Box {

            Column(
                modifier = Modifier
                    .width(800.dp)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(color1, color2, color3)
                        )
                    )
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                WeatherIcon(
                    Modifier.align(Alignment.CenterHorizontally),
                    curWeather
                )

                Spacer(modifier = Modifier.height(20.dp))

                // for Accessibility
                val accessibilityContent = stringResource(
                    id = R.string.current_weather,
                    WeatherDataProvider.dailyWeather[0].weather().text,
                    WeatherDataProvider.dailyWeather[0].temperature()
                        .displayName(LocalTemUnit.current)
                )

                WeatherInfoPager(
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally)
                        .semantics(true) {
                            contentDescription = accessibilityContent
                        },
                    pagerState = pagerState,
                    dailyWeathers = WeatherDataProvider.dailyWeather
                )

                HourlyWeatherChart(dailyWeather = selectedDay)

                Divider(
                    Modifier
                        .padding(top = 10.dp)
                        .height(0.5.dp)
                )

                DailyWeatherChart(
                    dailyWeathers = WeatherDataProvider.dailyWeather,
                    selectedIndex = selectedIndex
                ) {
                    selectedIndex = it
                }
            }

            ActionBar(tempUnit, setTempUnit)
        }
    }
}

/**
 * Pager showing weather info
 */
@Composable
fun WeatherInfoPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    dailyWeathers: List<DailyWeather>
) {
    Pager(
        state = pagerState,
        modifier = modifier
    ) {

        val day = dailyWeathers[page]

        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                day.dayOfMonth,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontType.fontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                day.weather().text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontType.fontFamily,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(100.dp)
            ) {

                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        "${day.temperatureRange.first.displayName(LocalTemUnit.current)}↑",
                        style = TextStyle(
                            fontSize = 19.sp,
                            fontFamily = FontType.fontFamily,
                            fontWeight = FontWeight.Normal,
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        "${day.temperatureRange.second.displayName(LocalTemUnit.current)}↓",
                        style = TextStyle(
                            fontSize = 19.sp,
                            fontFamily = FontType.fontFamily,
                            fontWeight = FontWeight.Normal,
                        ),
                        modifier = Modifier.align(Alignment.End)
                    )
                }

                val animateTween by rememberInfiniteTransition().animateFloat(
                    initialValue = -1f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        tween(2000, easing = LinearEasing),
                        RepeatMode.Reverse
                    )
                )

                Text(
                    day.temperature().displayName(LocalTemUnit.current),
                    style = TextStyle(
                        fontSize = 70.sp,
                        fontFamily = FontType.fontFamily,
                        fontWeight = FontWeight.SemiBold
                    ),
                    letterSpacing = 0.sp,
                    modifier = Modifier.offset(0.dp, (-5).dp * animateTween)
                )
                Text(
                    LocalTemUnit.current.text,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontType.fontFamily,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}

/**
 * Daily weather chart
 */
@Composable
fun DailyWeatherChart(
    modifier: Modifier = Modifier,
    dailyWeathers: List<DailyWeather>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit
) {

    Box(modifier = modifier.fillMaxWidth()) {

        val tempUnit = LocalTemUnit.current
        Canvas(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(bottom = 35.dp)
                .align(Alignment.BottomCenter)
        ) {

            val increment = size.width / dailyWeathers.size
            val min = dailyWeathers.minOf { it.averageTemperature }
            val max = dailyWeathers.maxOf { it.averageTemperature }
            val dy = (max - min).toFloat()

            val points = dailyWeathers.mapIndexed { index, dailyWeather ->
                Offset(
                    increment * index + increment / 2,
                    (1 - (dailyWeather.averageTemperature - min) / dy) * size.height
                )
            }

            val path = androidx.compose.ui.graphics.Path()
            path.moveTo(points.first().x, points.first().y)

            (0..points.size - 2).forEach { index ->
                path.lineTo(points[index + 1].x, points[index + 1].y)
            }

            drawIntoCanvas { canvas ->

                // draw path
                canvas.drawPath(
                    path,
                    androidx.compose.ui.graphics.Paint().apply {
                        style = PaintingStyle.Stroke
                        strokeWidth = 1.dp.toPx()
                        pathEffect = PathEffect.cornerPathEffect(50f)
                    }
                )

                // draw text
                val size = 10.sp.toPx()
                val textPaint = Paint().apply {
                    color = Color.Black.toArgb()
                    textSize = size
                    typeface = FontType.typeface
                }
                dailyWeathers.asSequence().zip(points.asSequence())
                    .forEachIndexed { index, pair ->
                        val (weather, points) = pair
                        // draw points
                        drawCircle(
                            Color.Black,
                            1.5.dp.toPx(),
                            Offset(points.x, points.y)
                        )

                        canvas.nativeCanvas.drawText(
                            weather.averageTemperature.displayName(tempUnit),
                            points.x - size / 2,
                            points.y + size * 1.5f,
                            textPaint
                        )
                    }
            }
        }

        Row(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        ) {
            (dailyWeathers.indices).forEach {

                // for Accessibility
                val accessibilityContent = stringResource(
                    id = R.string.average_weather,
                    dailyWeathers[it].dayOfWeekFull,
                    dailyWeathers[it].weather(),
                    dailyWeathers[it].temperature().displayName(LocalTemUnit.current)
                )
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            onSelect(it)
                        }
                        .semantics(mergeDescendants = true) {
                            contentDescription = accessibilityContent
                        }
                ) {
                    Column(
                        Modifier.align(Alignment.TopCenter)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = when (it) {
                                0 -> "Today"
                                1 -> "Tomorrow"
                                else -> dailyWeathers[it].dayOfWeek
                            },
                            style = TextStyle(fontSize = 10.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            if (it == selectedIndex) {
                                dailyWeathers[it].weather().animatableIcon()
                            } else {
                                dailyWeathers[it].weather().icon()
                            }
                        }
                    }
                }

                if (it < 6)
                    Divider(
                        Modifier
                            .height(120.dp)
                            .width(0.5.dp)
                            .padding(top = 20.dp)
                            .align(Alignment.Top)
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
                                    .align(Alignment.CenterHorizontally)
                                    .scale(0.6f)
                            ) {
                                it.weather.icon()
                            }
                            Text(
                                if (index < 12) "${(index + 1)} AM"
                                else "${(index - 11)} PM",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .clearAndSetSemantics { }
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
fun LineChart(
    modifier: Modifier,
    dailyWeather: DailyWeather
) {

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

    val tempUnit = LocalTemUnit.current
    Canvas(modifier) {

        val increment = size.width / dailyWeather.hourly.size
        val max = dailyWeather.hourly.maxOf { it.temperature }
        val min = dailyWeather.hourly.minOf { it.temperature }
        val dy = (max - min).toFloat()

        drawIntoCanvas { canvas ->

            if (cur != dailyWeather) {
                // change visible range according to animation
                canvas.clipRect(Rect(0f, 0f, size.width * animateFloat, size.height))
            }

            val path = Path()

            val points = dailyWeather.hourly.mapIndexed { index, hourlyWeather ->
                Offset(
                    increment * index + increment / 2,
                    (1 - (hourlyWeather.temperature - min) / dy) * (size.height * 0.3f) +
                        size.height * 0.2f // reserve space for drawText
                )
            }

            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)

            (0..points.size - 2).forEach { index ->

                val startP = points[index]
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

            path.lineTo(points.last().x + increment / 2, points.last().y)
            path.lineTo(points.last().x + increment / 2, size.height)
            path.lineTo(0f, size.height)
            path.lineTo(0f, points.first().y)

            // draw path
            canvas.nativeCanvas.drawPath(
                path,
                Paint().apply {
                    val linearGradient = LinearGradient(
                        0f, 0f,
                        0f, 200f,
                        Color.Black.copy(alpha = 0.1f).toArgb(),
                        Color.Transparent.toArgb(),
                        Shader.TileMode.CLAMP
                    )

                    shader = linearGradient
                    style = Paint.Style.FILL
                    isAntiAlias = true
                }
            )

            // draw points
            canvas.drawPoints(
                PointMode.Points, points,
                androidx.compose.ui.graphics.Paint().apply {
                    strokeWidth = 8f
                    strokeCap = StrokeCap.Round
                    color = Color.Black.copy(0.6f)
                }
            )

            // draw text
            val size = 10.sp.toPx()
            val textPaint = Paint().apply {
                color = Color.Black.toArgb()
                textSize = size
                alpha = 90
                typeface = FontType.typeface
            }
            dailyWeather.hourly.asSequence().zip(points.asSequence())
                .forEachIndexed { index, pair ->
                    val (weather, points) = pair
                    canvas.nativeCanvas.drawText(
                        weather.temperature.displayName(tempUnit),
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

    DisposableEffect(weatherIcon.composedIcon) {
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
        cur.composedIcon + (weatherIcon.composedIcon - cur.composedIcon) * animateFloat
    }

    ComposedIcon(
        modifier,
        composeInfo
    )
}

@Composable
private fun backgroundColorState(target: Color) =
    animateColorAsState(
        targetValue = target,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

/**
 * transparent ActionBar with more-opt menu
 */
@Composable
fun ActionBar(selected: TemperatureUnit, onSelect: (TemperatureUnit) -> Unit) {
    Box(
        Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(
                // TODO: auto hide
                Brush.verticalGradient(
                    listOf(Color.Black.copy(alpha = 0.4f), Color.Transparent)
                )
            )
            .clearAndSetSemantics { }
    ) {
        var showDialogState by remember { mutableStateOf(false) }

        if (showDialogState) {
            ShowDialog(selected) {
                onSelect(it)
                showDialogState = false
            }
        }

        Image(
            Icons.Default.MoreVert,
            "",
            Modifier
                .size(50.dp)
                .offset((-2).dp, 30.dp)
                .clickable { showDialogState = true }
                .padding(10.dp)
                .align(Alignment.TopEnd),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}
