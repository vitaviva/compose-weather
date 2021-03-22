package com.example.androiddevchallenge.data

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.Cloud
import com.example.androiddevchallenge.ComposeInfo
import com.example.androiddevchallenge.IconInfo
import com.example.androiddevchallenge.Sun
import com.example.androiddevchallenge.data.WeatherBackground.clear
import com.example.androiddevchallenge.data.WeatherBackground.cloud
import com.example.androiddevchallenge.ui.theme.teal200
import com.example.androiddevchallenge.ui.theme.teal500
import com.example.androiddevchallenge.ui.theme.teal700


enum class Weather(
    val text: String = "",
    val animatable: ComposeInfo,
    val icon: @Composable () -> Unit,
    val background: BgColors
) {
    Sunny(
        "Clear",
        ComposeInfo(
            sun = IconInfo(200.dp, 0.dp to 0.dp),
            cloud = IconInfo(200.dp, (-100).dp to 0.dp, 0f),
            rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
        ), { Sun(Modifier.size(40.dp)) }, clear
    ),
    Sunnyrain(
        "Cloudy with occasional showers",
        ComposeInfo(
            sun = IconInfo(120.dp, 80.dp to 0.dp),
            cloud = IconInfo(200.dp, 0.dp to 0.dp),
            rains = IconInfo(80.dp, 45.dp to 60.dp),
        ), { Cloud(Modifier.size(40.dp)) }, cloud
    ),
    Cloud(
        "Cloudy",
        ComposeInfo(
            sun = IconInfo(120.dp, 80.dp to 0.dp, alpha = 0f),
            cloud = IconInfo(200.dp, 0.dp to 0.dp),
            rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
        ), { Cloud(Modifier.size(40.dp)) }, cloud
    )
}


/**
 * background color for each type of weather
 */
typealias BgColors = Triple<Color, Color, Color>
object WeatherBackground {

    val cloud = BgColors(
        Color.LightGray,
        Color.Gray,
        Color.White
    )

    val clear = BgColors(
        teal700,
        teal500,
        Color.White
    )

}
