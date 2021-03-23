package com.example.androiddevchallenge.ui

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
