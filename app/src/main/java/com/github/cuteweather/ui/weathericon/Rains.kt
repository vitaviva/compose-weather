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

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
private fun AnimatableRaindrop(modifier: Modifier = Modifier, durationMillis: Int = 800) {

    val transition = rememberInfiniteTransition()

    val animateTween by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    Raindrop(modifier, animateTween)
}

@Composable
private fun Raindrop(modifier: Modifier = Modifier, spacePosition: Float = 0f) {

    Canvas(modifier) {

        // scope
        val width = size.width
        val x: Float = size.width / 2
        val scopeHeight = size.height - width / 2

        // space
        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * spacePosition
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        // line length
        val lineHeight = scopeHeight - space

        // line1
        val line1y1 = max(0f, sy1 - lineHeight)
        val line1y2 = max(line1y1, sy1)

        // line2
        val line2y1 = min(sy2, scopeHeight)
        val line2y2 = min(line2y1 + lineHeight, scopeHeight)

        // draw
        drawLine(
            Color.Black,
            Offset(x, line1y1),
            Offset(x, line1y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Color.Black
            ),
            cap = StrokeCap.Round
        )

        drawLine(
            Color.Black,
            Offset(x, line2y1),
            Offset(x, line2y2),
            strokeWidth = width,
            colorFilter = ColorFilter.tint(
                Color.Black
            ),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun AnimatableRains(modifier: Modifier = Modifier, lightRain: Boolean = false) {
    Rains(modifier, true, lightRain)
}

@Composable
fun Rains(
    modifier: Modifier = Modifier,
    animate: Boolean = false,
    lightRain: Boolean = false,
) {

    Layout(
        modifier = modifier.rotate(30f),
        content = {

            if (animate) {
                AnimatableRaindrop(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    500
                )
                AnimatableRaindrop(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    600
                )
                if (!lightRain) {
                    AnimatableRaindrop(
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        600
                    )
                }
            } else {
                Raindrop(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                Raindrop(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                )
                if (!lightRain) {
                    Raindrop(
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    )
                }
            }
        }
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each children

            val height = when (index) {
                0 -> constraints.maxHeight * 0.8f
                1 -> constraints.maxHeight * 0.9f
                2 -> constraints.maxHeight * 0.6f
                else -> 0f
            }
            measurable.measure(
                constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = constraints.maxWidth / 10, // raindrop width
                    maxHeight = height.toInt(),
                )
            )
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = constraints.maxWidth / ((placeables.size + 1) * 2)

            // Place children in the parent layout
            placeables.forEachIndexed { index, placeable ->
                // Position item on the screen
                placeable.place(x = xPosition, y = 0)

                // Record the y co-ord placed up to
                xPosition += (constraints.maxWidth / ((placeables.size + 1) * 0.8f)).roundToInt()
            }
        }
    }
}

@Preview
@Composable
fun PreviewRains() {
    Row {
        Column {
            Rains(modifier = Modifier.size(150.dp))
            Spacer(Modifier.height(5.dp))
            AnimatableRains(modifier = Modifier.size(150.dp))
        }
        Divider(
            Modifier
                .height(300.dp)
                .padding(10.dp)
                .width(1.dp)
        )
        Column {
            Rains(modifier = Modifier.size(150.dp), lightRain = true)
            Spacer(Modifier.height(5.dp))
            AnimatableRains(modifier = Modifier.size(150.dp), lightRain = true)
        }
    }
}
