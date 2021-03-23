package com.example.androiddevchallenge.ui.weathericon

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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

        //scope
        val width = size.width
        val x: Float = size.width / 2
        val scopeHeight = size.height - width / 2

        //space
        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * spacePosition
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        //line length
        val lineHeight = scopeHeight - space

        //line1
        val line1y1 = max(0f, sy1 - lineHeight)
        val line1y2 = max(line1y1, sy1)

        //line2
        val line2y1 = min(sy2, scopeHeight)
        val line2y2 = min(line2y1 + lineHeight, scopeHeight)

        //draw
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
fun AnimatableRains(modifier: Modifier = Modifier) {
    Rains(modifier, true)
}

@Composable
fun Rains(
    modifier: Modifier = Modifier,
    animate: Boolean = false
) {

    Layout(modifier = modifier.rotate(30f), content = {

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
            AnimatableRaindrop(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                600
            )

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
            Raindrop(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
        }

    }) { measurables, constraints ->
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
                    maxWidth = constraints.maxWidth / 10,
                    maxHeight = height.toInt(),
                )
            )
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var xPosition = constraints.maxWidth / 8

            // Place children in the parent layout
            placeables.forEachIndexed { index, placeable ->
                // Position item on the screen
                placeable.place(x = xPosition, y = 0)

                // Record the y co-ord placed up to
                xPosition += (constraints.maxWidth / 3.4).roundToInt()
            }
        }
    }


}

@Preview
@Composable
fun PreviewRains() {
    Column {
        Rains(modifier = Modifier.size(300.dp))
        AnimatableRains(modifier = Modifier.size(300.dp))
    }
}
