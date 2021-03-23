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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [ComposedIcon] use this info to compose icons
 */
data class ComposeInfo(
    val sun: IconInfo,
    val cloud: IconInfo,
    val lightCloud: IconInfo,
    val rains: IconInfo,
    val lightRain: IconInfo
) {
    operator fun times(float: Float): ComposeInfo =
        copy(
            sun = sun * float,
            cloud = cloud * float,
            lightCloud = lightCloud * float,
            rains = rains * float,
            lightRain = lightRain * float
        )

    operator fun minus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun - composeInfo.sun,
            cloud = cloud - composeInfo.cloud,
            lightCloud = lightCloud - composeInfo.lightCloud,
            rains = rains - composeInfo.rains,
            lightRain = lightRain - composeInfo.lightRain,
        )

    operator fun plus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun + composeInfo.sun,
            cloud = cloud + composeInfo.cloud,
            lightCloud = lightCloud + composeInfo.lightCloud,
            rains = rains + composeInfo.rains,
            lightRain = lightRain + composeInfo.lightRain
        )
}

/**
 * properties info of each icon
 */
data class IconInfo(
    val size: Dp,
    val offset: Pair<Dp, Dp> = 0.dp to 0.dp,
    val alpha: Float = 1f,
) {
    operator fun times(float: Float): IconInfo =
        copy(
            size = size * float,
            offset = offset.first * float to offset.second * float,
            alpha = alpha * float,
        )

    operator fun minus(iconInfo: IconInfo): IconInfo =
        copy(
            size = size - iconInfo.size,
            offset = offset.first - iconInfo.offset.first to offset.second - iconInfo.offset.second,
            alpha = alpha - iconInfo.alpha,
        )

    operator fun plus(iconInfo: IconInfo): IconInfo =
        copy(
            size = size + iconInfo.size,
            offset = offset.first + iconInfo.offset.first to offset.second + iconInfo.offset.second,
            alpha = alpha + iconInfo.alpha,
        )
}
