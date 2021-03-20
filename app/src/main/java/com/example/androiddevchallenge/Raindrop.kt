package com.example.androiddevchallenge

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun Raindrop(modifier: Modifier = Modifier, durationMillis: Int = 800) {

    val transition = rememberInfiniteTransition()

    val animateTween by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis, easing = LinearEasing),
            RepeatMode.Restart
        )
    )


    Canvas(modifier) {

        //scope
        val width = size.width
        val x: Float = size.width / 2
        val scopeHeight = size.height - width / 2

        //space
        val space = size.height / 2.2f + width / 2
        val spacePos = scopeHeight * animateTween
        val sy1 = spacePos - space / 2
        val sy2 = spacePos + space / 2

        //line height
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

@Deprecated("")
@Composable
fun Rains2(
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .size(300.dp)
            .rotate(30f)
    ) {
        Spacer(modifier = modifier.weight(1f))

        Raindrop(
            Modifier
                .width(30.dp)
                .height(240.dp),
            500
        )
        Spacer(modifier = modifier.weight(1f))
        Raindrop(
            Modifier
                .width(30.dp)
                .height(250.dp),
            600
        )
        Spacer(modifier = modifier.weight(1f))
        Raindrop(
            Modifier
                .width(30.dp)
                .height(180.dp),
            400
        )
        Spacer(modifier = modifier.weight(1f))

    }


}


@Composable
fun Rains(
    modifier: Modifier = Modifier,
) {

    Layout(modifier = modifier.rotate(30f), content = {


        Raindrop(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            500
        )
        Raindrop(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            600
        )
        Raindrop(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            400
        )

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
fun previewRains() {
    Rains(modifier = Modifier.size(300.dp))
}

//@Preview
//@Composable
//fun previewRains2() {
//    Rains2()
//}