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

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.cuteweather.R

@Composable
fun AnimatableThunder(modifier: Modifier = Modifier, durationMillis: Int = 600) {

    val transition = rememberInfiniteTransition()

    val animateAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis,
                easing = CubicBezierEasing(0f, 0.2f, 0.7f, 1f),
                delayMillis = durationMillis * 3
            ),
            RepeatMode.Reverse
        )
    )

    val animateY by transition.animateFloat(
        initialValue = -5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis / 10, easing = LinearEasing,
                delayMillis = durationMillis / 5
            ),
            RepeatMode.Reverse
        )
    )

    Thunder(
        modifier = modifier
            .alpha(alpha = animateAlpha % 2f)
            .offset(0.dp, animateY.dp)
    )
}

@Composable
fun Thunder(modifier: Modifier = Modifier) {

    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_thunder),
        contentDescription = "",
        modifier = modifier.clearAndSetSemantics { }
    )
}

@Preview
@Composable
fun PreviewAnimatableThunder() {
    AnimatableThunder()
}
