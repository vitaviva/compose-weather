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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.github.cuteweather.data.WeatherComposedInfo.IconSize
import com.github.cuteweather.ui.weathericon.AnimatableCloud
import com.github.cuteweather.ui.weathericon.AnimatableRains
import com.github.cuteweather.ui.weathericon.AnimatableSun

/**
 * show current weather's animation on top of [WeatherView]
 */
@Composable
fun ComposedIcon(modifier: Modifier = Modifier, composeInfo: ComposeInfo) {

    val (sun, cloud, lightCloud, rains, lightRain) = composeInfo
    Box(modifier = modifier.size(IconSize)) {

        val _modifier = remember(Unit) {
            { icon: IconInfo ->
                Modifier
                    .offset(
                        icon.size * icon.offset.x,
                        icon.size * icon.offset.y
                    )
                    .size(icon.size * icon.sizeRatio)
                    .alpha(icon.alpha)
            }
        }

        AnimatableSun(_modifier(sun))

        AnimatableRains(_modifier(rains))

        AnimatableRains(_modifier(lightRain), true) // light rain

        AnimatableCloud(_modifier(cloud))

        AnimatableCloud(_modifier(lightCloud), 1000) // light cloud
    }
}
