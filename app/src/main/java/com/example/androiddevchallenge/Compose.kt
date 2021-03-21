package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Weather

@Deprecated("")
@Composable
fun ComposedIcon2() {
    Box(modifier = Modifier.size(width = 300.dp, height = 300.dp)) {

        Sun2(
            modifier = Modifier
                .scale(0.9f)
                .offset(90.dp, 0.dp)
        )

        Rains2(
            Modifier
                .scale(0.4f)
                .offset(-100.dp, 240.dp)
        )
        Cloud(
            modifier = Modifier
                .scale(1.1f)
                .offset(0.dp, 50.dp)
        )
    }
}


@Composable
fun ComposedIcon(modifier: Modifier = Modifier, composeInfo: ComposeInfo) {

    val (sun, cloud, rains) = composeInfo
    Box(modifier = modifier.size(200.dp)) {

        AnimatableSun(
            Modifier
                .size(sun.size)
                .offset(sun.offset.first, sun.offset.second)
                .alpha(sun.alpha)
        )

        Rains(
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
        )

    }
}


@Preview
@Composable
fun PreviewSunrain() {
    ComposedIcon(composeInfo = Weather.Sunnyrain.animatableIcon)
}


@Preview
@Composable
fun PreviewSunny() {
    ComposedIcon(composeInfo = Weather.Sunny.animatableIcon)
}



@Preview
@Composable
fun PreviewCloud() {
    ComposedIcon(composeInfo = Weather.Cloud.animatableIcon)
}