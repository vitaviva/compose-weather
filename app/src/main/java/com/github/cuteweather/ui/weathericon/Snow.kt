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

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
private fun AnimatableSnowdrop(modifier: Modifier = Modifier, durationMillis: Int = 1000) {

    val transition = rememberInfiniteTransition()

    val animateY by transition.animateFloat(
        initialValue = 0f,
        targetValue = 2.5f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    val animateX by transition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis / 3, easing = LinearEasing),
            RepeatMode.Reverse
        )
    )

    val animateAlpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis, easing = FastOutSlowInEasing),

        )
    )

    Snowdrop(modifier, animateX, animateY, animateAlpha)
}

@Composable
private fun Snowdrop(
    modifier: Modifier = Modifier,
    xOffset: Float = 1f,
    yOffset: Float = 1f,
    alpha: Float = 1f
) {

    Canvas(modifier) {

        val radius = size.width / 2

        val _center = center.copy(
            x = center.x + center.x * xOffset,
            y = center.y + center.y * yOffset
        )

        drawCircle(
            color = Color.White.copy(alpha = alpha),
            center = _center,
            radius = radius,
        )

        drawCircle(
            color = Color.Black.copy(alpha = alpha),
            center = _center,
            radius = radius,
            style = Stroke(width = radius * 0.5f)
        )
    }
}

@Composable
fun AnimatableSnow(modifier: Modifier = Modifier) {
    Snow(modifier, true)
}

@Composable
fun Snow(
    modifier: Modifier = Modifier,
    animate: Boolean = false,
) {

    Layout(
        modifier = modifier,
        content = {

            if (animate) {
                AnimatableSnowdrop(
                    modifier.fillMaxSize(),
                    2200
                )
                AnimatableSnowdrop(
                    modifier.fillMaxSize(),
                    1600
                )
                AnimatableSnowdrop(
                    modifier.fillMaxSize(),
                    1800
                )
            } else {
                Snowdrop(
                    modifier.fillMaxSize()
                )
                Snowdrop(
                    modifier.fillMaxSize()
                )
                Snowdrop(
                    modifier.fillMaxSize()
                )
            }
        }
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each children

            val height = when (index) {
                0 -> constraints.maxHeight * 0.6f
                1 -> constraints.maxHeight * 1.0f
                2 -> constraints.maxHeight * 0.7f
                else -> 0f
            }
            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth / 5, // snowdrop width
                    maxHeight = height.roundToInt(),
                )
            )
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = constraints.maxWidth / ((placeables.size + 1))

            // Place children in the parent layout
            placeables.forEachIndexed { index, placeable ->
                // Position item on the screen
                placeable.place(x = xPosition, y = -(constraints.maxHeight * 0.2).roundToInt())

                // Record the y co-ord placed up to
                xPosition += (constraints.maxWidth / ((placeables.size + 1) * 0.9f)).roundToInt()
            }
        }
    }
}

@Preview
@Composable
fun PreviewSnow() {
    Row {
        Column {
            AnimatableSnow(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
            )
        }
    }
}
