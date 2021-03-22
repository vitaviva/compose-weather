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
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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

        WeatherIcon(
            Modifier.align(Alignment.CenterHorizontally),
            curWeather
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                                ),modifier = Modifier.align(Alignment.End)
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                "${day.temperatureRange.second}↓",
                                style = TextStyle(
                                    fontSize = 19.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold,
                                ),modifier = Modifier.align(Alignment.End)
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

        DailyWeatherChart(dailyWeathers = dailyWeather) {
            selectedIndex = it
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
    onSelect: (index: Int) -> Unit
) {

    Box(modifier = modifier.fillMaxWidth()) {

        Canvas(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {

            val increment = size.width / dailyWeathers.size
            val min = dailyWeather.minOf { it.averageTemperature }
            val max = dailyWeather.maxOf { it.averageTemperature }
            val dy = (max - min).toFloat()

            val points = dailyWeather.mapIndexed { index, dailyWeather ->
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

                //draw path
                canvas.drawPath(path, androidx.compose.ui.graphics.Paint().apply {
                    style = PaintingStyle.Stroke
                    strokeWidth = 0.5.dp.toPx()
                    pathEffect = PathEffect.cornerPathEffect(40f)
                })


                //draw text
                val size = 10.sp.toPx()

                val textPaint = Paint().apply {
                    color = Color.Black.toArgb()
                    textSize = size
                    typeface = Typeface.MONOSPACE

                }
                dailyWeather.asSequence().zip(points.asSequence())
                    .forEachIndexed { index, pair ->
                        val (weather, points) = pair
                        //draw round background
                        val radius = size * 0.9f
                        drawCircle(Color.White, radius, Offset(points.x + size / 4, points.y))
                        drawCircle(
                            Color.Black,
                            radius,
                            Offset(points.x + size / 6, points.y + size / 10),
                            style = Stroke(width = 1.dp.toPx())
                        )

                        canvas.nativeCanvas.drawText(
                            "${weather.averageTemperature}",
                            points.x - size / 2,
                            points.y + size / 2,
                            textPaint
                        )
                    }
            }
        }


        Row(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
        ) {
            (dailyWeathers.indices).forEach {

                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            onSelect(it)
                        }) {
                    Column(
                        Modifier.align(Alignment.TopCenter)
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))

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
                            dailyWeathers[it].weather.icon()
                        }

                    }
                }


                if (it < 6)
                    Divider(
                        Modifier
                            .height(80.dp)
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
                    (1 - (hourlyWeather.temperature - min) / dy) * (size.height * 0.3f)
                            + size.height * 0.2f //reserve space for drawText
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

            //draw path
            canvas.nativeCanvas.drawPath(path, Paint().apply {
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
            })

            //draw points
            canvas.drawPoints(PointMode.Points, points, androidx.compose.ui.graphics.Paint().apply {
                strokeWidth = 8f
                strokeCap = StrokeCap.Round
                color = Color.Black.copy(0.6f)
            })

            //draw text
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
