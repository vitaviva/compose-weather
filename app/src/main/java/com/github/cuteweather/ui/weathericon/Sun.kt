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
package com.github.cuteweather.ui.weathericon

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatableSun(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()

    val animateTween by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(5000), RepeatMode.Restart)
    )

    Canvas(modifier.rotate(animateTween)) {

        val radius = size.width / 6
        val stroke = size.width / 20
        val centerOffset = Offset(size.width / 30, size.width / 30)

        // draw circle
        drawCircle(
            color = Color.Black,
            radius = radius + stroke / 2,
            style = Stroke(width = stroke),
            center = center + centerOffset
        )
        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill,
            center = center + centerOffset
        )

        // draw line

        val lineLength = radius * 0.6f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX + centerOffset.x
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY + centerOffset.y
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun Sun(modifier: Modifier = Modifier) {

    Canvas(modifier) {

        val radius = size.width / 6
        val stroke = size.width / 20

        // draw circle
        drawCircle(
            color = Color.Black,
            radius = radius + stroke / 2,
            style = Stroke(width = stroke),
        )
        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill,
        )

        // draw line

        val lineLength = radius * 0.2f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimatableSun() {
    AnimatableSun(Modifier.size(100.dp))
}
