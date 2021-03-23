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

        //light rain
        AnimatableRains(
            Modifier
                .size(lightRain.size)
                .offset(lightRain.offset.first, lightRain.offset.second)
                .alpha(lightRain.alpha), true
        )

        AnimatableCloud(
            Modifier
                .size(cloud.size)
                .offset(cloud.offset.first, cloud.offset.second)
                .alpha(cloud.alpha)
                .padding(start = 20.dp, end = 20.dp)
        )

        //light cloud
        AnimatableCloud(
            Modifier
                .size(lightCloud.size)
                .offset(lightCloud.offset.first, lightCloud.offset.second)
                .alpha(lightCloud.alpha),
            1000
        )
    }
}
