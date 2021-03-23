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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.github.cuteweather.ui.weathericon.AnimatableCloud
import com.github.cuteweather.ui.weathericon.AnimatableRains
import com.github.cuteweather.ui.weathericon.AnimatableSun

/**
 * show current weather's animation on top of [WeatherView]
 */
@Composable
fun ComposedIcon(modifier: Modifier = Modifier, composeInfo: ComposeInfo) {

    val (sun, cloud, lightCloud, rains, lightRain) = composeInfo
    Box(modifier = modifier.size(200.dp)) {

        AnimatableSun(
            Modifier
                .size(sun.size)
                .offset(sun.offset.first, sun.offset.second)
                .alpha(sun.alpha)
        )

        AnimatableRains(
            Modifier
                .size(rains.size)
                .offset(rains.offset.first, rains.offset.second)
                .alpha(rains.alpha)
        )

        // light rain
        AnimatableRains(
            Modifier
                .size(lightRain.size)
                .offset(lightRain.offset.first, lightRain.offset.second)
                .alpha(lightRain.alpha),
            true
        )

        AnimatableCloud(
            Modifier
                .size(cloud.size)
                .offset(cloud.offset.first, cloud.offset.second)
                .alpha(cloud.alpha)
                .padding(start = 20.dp, end = 20.dp)
        )

        // light cloud
        AnimatableCloud(
            Modifier
                .size(lightCloud.size)
                .offset(lightCloud.offset.first, lightCloud.offset.second)
                .alpha(lightCloud.alpha),
            1000
        )
    }
}
