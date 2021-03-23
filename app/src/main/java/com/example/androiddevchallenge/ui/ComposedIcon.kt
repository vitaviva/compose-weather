package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.weathericon.AnimatableCloud
import com.example.androiddevchallenge.ui.weathericon.AnimatableRains
import com.example.androiddevchallenge.ui.weathericon.AnimatableSun

/**
 * show current weather's animation on top of [WeatherView]
 */
@Composable
fun ComposedIcon(modifier: Modifier = Modifier, composeInfo: ComposeInfo) {

    val (sun, cloud, cloud2, rains) = composeInfo
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


        AnimatableCloud(
            Modifier
                .size(cloud.size)
                .offset(cloud.offset.first, cloud.offset.second)
                .alpha(cloud.alpha)
                .padding(start = 20.dp, end = 20.dp)
        )

        AnimatableCloud(
            Modifier
                .size(cloud2.size)
                .offset(cloud2.offset.first, cloud2.offset.second)
                .alpha(cloud2.alpha),
            1000
        )
    }
}
