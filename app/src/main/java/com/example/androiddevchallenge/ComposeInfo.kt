package com.example.androiddevchallenge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class ComposeInfo(
    val sun: IconInfo,
    val cloud: IconInfo,
    val rains: IconInfo
) {
    operator fun times(float: Float): ComposeInfo =
        copy(
            sun = sun * float,
            cloud = cloud * float,
            rains = rains * float,
        )

    operator fun minus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun - composeInfo.sun,
            cloud = cloud - composeInfo.cloud,
            rains = rains - composeInfo.rains,
        )


    operator fun plus(composeInfo: ComposeInfo): ComposeInfo =
        copy(
            sun = sun + composeInfo.sun,
            cloud = cloud + composeInfo.cloud,
            rains = rains + composeInfo.rains,
        )

}

data class IconInfo(
    val size: Dp,
    val offset: Pair<Dp, Dp> = 0.dp to 0.dp,
    val alpha: Float = 1f,
    val scale: Float = 1f
) {
    operator fun times(float: Float): IconInfo =
        copy(
            size = size * float,
            offset = offset.first * float to offset.second * float,
            alpha = alpha * float,
            scale = scale * float
        )

    operator fun minus(iconInfo: IconInfo): IconInfo =
        copy(
            size = size - iconInfo.size,
            offset = offset.first - iconInfo.offset.first to offset.second - iconInfo.offset.second,
            alpha = alpha - iconInfo.alpha,
            scale = scale - iconInfo.scale
        )

    operator fun plus(iconInfo: IconInfo): IconInfo =
        copy(
            size = size + iconInfo.size,
            offset = offset.first + iconInfo.offset.first to offset.second + iconInfo.offset.second,
            alpha = alpha + iconInfo.alpha,
            scale = scale + iconInfo.scale
        )
}
