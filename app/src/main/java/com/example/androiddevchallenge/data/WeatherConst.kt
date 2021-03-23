package com.example.androiddevchallenge.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.ClearAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.RainAnimatableIcon
import com.example.androiddevchallenge.data.WeatherBackground.ClearBg
import com.example.androiddevchallenge.data.WeatherBackground.CloudyBg
import com.example.androiddevchallenge.data.WeatherBackground.RainBg
import com.example.androiddevchallenge.data.WeatherComposedInfo.ClearComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.CloudyComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.CloudyRainsComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.MostlyClearComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.RainsComposed
import com.example.androiddevchallenge.data.WeatherIcon.ClearIcon
import com.example.androiddevchallenge.data.WeatherIcon.CloudyIcon
import com.example.androiddevchallenge.data.WeatherIcon.RainIcon
import com.example.androiddevchallenge.ui.ComposeInfo
import com.example.androiddevchallenge.ui.ComposedIcon
import com.example.androiddevchallenge.ui.IconInfo
import com.example.androiddevchallenge.ui.theme.teal500
import com.example.androiddevchallenge.ui.theme.teal700
import com.example.androiddevchallenge.ui.theme.teal900
import com.example.androiddevchallenge.ui.weathericon.AnimatableCloud
import com.example.androiddevchallenge.ui.weathericon.AnimatableRains
import com.example.androiddevchallenge.ui.weathericon.AnimatableSun
import com.example.androiddevchallenge.ui.weathericon.Cloud
import com.example.androiddevchallenge.ui.weathericon.Rains
import com.example.androiddevchallenge.ui.weathericon.Sun


enum class Weather(
    val text: String = "",
    val composedIcon: ComposeInfo,
    val icon: @Composable () -> Unit,
    val animatableIcon: @Composable () -> Unit,
    val background: BgColors
) {
    Clear(
        "Clear",
        ClearComposed, ClearIcon, ClearAnimatableIcon, ClearBg
    ),
    MostlyClear(
        "Clear with periodic clouds",
        MostlyClearComposed, ClearIcon, ClearAnimatableIcon, ClearBg
    ),

    Cloudy(
        "Cloudy",
        CloudyComposed, CloudyIcon, CloudyAnimatableIcon, CloudyBg
    ),

    CloudyRains(
        "Cloudy with periodic showers",
        CloudyRainsComposed, RainIcon, RainAnimatableIcon, RainBg
    ),

    Rains(
        "Rain",
        RainsComposed, RainIcon, RainAnimatableIcon, RainBg
    )
}


/**
 * background color for each type of weather
 */
typealias BgColors = Triple<Color, Color, Color>

object WeatherBackground {

    val RainBg = BgColors(
        Color.LightGray,
        Color.Gray,
        Color.White
    )

    val CloudyBg = BgColors(
        teal700,
        teal500,
        Color.White
    )

    val ClearBg = BgColors(
        teal500,
        teal900,
        teal500
    )

}

/**
 * used in central area
 */
object WeatherComposedInfo {

    val ClearComposed = ComposeInfo(
        sun = IconInfo(200.dp, 0.dp to 0.dp),
        cloud = IconInfo(200.dp, (-100).dp to 0.dp, 0f),
        cloud2 = IconInfo(100.dp, 10.dp to 0.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
    )

    val MostlyClearComposed = ComposeInfo(
        sun = IconInfo(200.dp, 0.dp to 0.dp),
        cloud = IconInfo(200.dp, (-100).dp to 0.dp, 0f),
        cloud2 = IconInfo(100.dp, 10.dp to 0.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
    )

    val CloudyComposed = ComposeInfo(
        sun = IconInfo(120.dp, 80.dp to 0.dp, alpha = 0f),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        cloud2 = IconInfo(100.dp, 10.dp to 25.dp),
        rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
    )

    val CloudyRainsComposed = ComposeInfo(
        sun = IconInfo(120.dp, 80.dp to 0.dp),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        cloud2 = IconInfo(90.dp, 0.dp to 0.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp),
    )

    val RainsComposed = ComposeInfo(
        sun = IconInfo(120.dp, 80.dp to 0.dp, alpha = 0f),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        cloud2 = IconInfo(100.dp, 0.dp to 0.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
    )
}


/**
 * used in chart or navigation bar
 */
object WeatherIcon {
    val ClearIcon = @Composable {
        Sun(Modifier.size(40.dp))
    }

    val CloudyIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            Cloud(
                Modifier
                    .size(35.dp)
                    .align(Alignment.Center)
            )
        }

    }

    val RainIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            Rains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp),
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}


/**
 * used in navigation bar for selected item
 */
object WeatherAnimatableIcon {
    val ClearAnimatableIcon = @Composable {
        AnimatableSun(Modifier.size(40.dp))
    }

    val CloudyAnimatableIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            AnimatableCloud(
                Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                800
            )
        }

    }

    val RainAnimatableIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            AnimatableRains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp)
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }
}


@Preview
@Composable
fun PreviewClearIcon() {
    Row {
        ClearIcon()
        ClearAnimatableIcon()
    }
}


@Preview
@Composable
fun PreviewCloudyIcon() {
    Row {
        CloudyIcon()
        CloudyAnimatableIcon()
    }
}


@Preview
@Composable
fun PreviewRainIcon() {
    RainIcon()
}


@Preview
@Composable
fun PreviewCloudyRains() {
    ComposedIcon(composeInfo = CloudyRainsComposed)
}


@Preview
@Composable
fun PreviewClear() {
    ComposedIcon(composeInfo = ClearComposed)
}


@Preview
@Composable
fun PreviewCloudy() {
    ComposedIcon(composeInfo = CloudyComposed)
}

