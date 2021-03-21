package com.example.androiddevchallenge.data

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.Cloud
import com.example.androiddevchallenge.ComposeInfo
import com.example.androiddevchallenge.IconInfo
import com.example.androiddevchallenge.Sun


enum class Weather(
    val animatableIcon: ComposeInfo,
    val icon: @Composable () -> Unit
) {
    Sunny(
        ComposeInfo(
            sun = IconInfo(200.dp, 0.dp to 0.dp),
            cloud = IconInfo(200.dp, (-100).dp to 0.dp, 0f),
            rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
        ), { Sun(Modifier.size(40.dp)) }
    ),
    Sunnyrain(
        ComposeInfo(
            sun = IconInfo(120.dp, 80.dp to 0.dp),
            cloud = IconInfo(200.dp, 0.dp to 0.dp),
            rains = IconInfo(80.dp, 45.dp to 60.dp),
        ), { Cloud(Modifier.size(40.dp)) }
    ),
    Cloud(
        ComposeInfo(
            sun = IconInfo(120.dp, 80.dp to 0.dp, alpha = 0f),
            cloud = IconInfo(200.dp, 0.dp to 0.dp),
            rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
        ), { Cloud(Modifier.size(40.dp)) }
    )
}