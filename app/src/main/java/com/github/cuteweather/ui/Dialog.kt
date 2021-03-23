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
package com.github.cuteweather.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.cuteweather.data.TemperatureUnit

@Composable
fun ShowDialog(selected: TemperatureUnit, onSelect: (TemperatureUnit) -> Unit) {
    AlertDialog(
        title = {},
        text = {
            DialogContent(Modifier, selected, onSelect)
        },
        shape = RoundedCornerShape(20.dp),
        buttons = {},
        onDismissRequest = { onSelect(selected) }
    )
}

@Composable
private fun DialogContent(
    modifier: Modifier = Modifier,
    selected: TemperatureUnit,
    onSelect: (TemperatureUnit) -> Unit
) {

    Box(modifier) {

        var animationTrigger by remember {
            mutableStateOf(
                if (selected == TemperatureUnit.Fahrenheit) 0f else 1f
            )
        }
        val animateFloat by animateFloatAsState(
            targetValue = animationTrigger,
            finishedListener = {
                if (it == 0f && selected != TemperatureUnit.Fahrenheit) {
                    onSelect(TemperatureUnit.Fahrenheit)
                } else if (it == 1f && selected != TemperatureUnit.Centigrade) {
                    onSelect(TemperatureUnit.Centigrade)
                }
            }
        )

        Row(
            Modifier
                .width(200.dp)
                .height(100.dp)
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .clickable { animationTrigger = 0f }
            ) {

                Text(
                    text = TemperatureUnit.Fahrenheit.text,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 30.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = Color.Black,
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .clickable { animationTrigger = 1f }
            ) {
                Text(
                    text = TemperatureUnit.Centigrade.text,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 30.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = Color.Black
                )
            }
        }

        Canvas(
            modifier = Modifier
                .width(60.dp)
                .height(50.dp)
                .align(Alignment.Center)
        ) {

            val strokeWidth = 8f

            val x1 = size.height * 0.25f
            val x2 = size.width - x1
            val y1 = size.height * 0.25f
            val y2 = size.height - y1

            drawLine(Color.Black, Offset(x1, y1), Offset(x2, y1), strokeWidth = strokeWidth)

            drawLine(Color.Black, Offset(x1, y2), Offset(x2, y2), strokeWidth = strokeWidth)

            val sz = Size(size.height * 0.5f, size.height * 0.5f)
            drawArc(
                Color.Black,
                90f,
                180f,
                size = sz,
                topLeft = Offset(0f, size.height * 0.25f),
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            drawArc(
                Color.Black,
                -90f,
                180f,
                size = sz,
                topLeft = Offset(x2 - x1, size.height * 0.25f),
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            val radius = size.width * 0.1f
            drawCircle(
                Color.Black,
                center = Offset(x1 + (x2 - x1) * animateFloat, size.height * 0.5f),
                radius = radius
            )
        }
    }
}

@Preview
@Composable
fun PreivewDialog() {
    DialogContent(selected = TemperatureUnit.Fahrenheit) {}
}
