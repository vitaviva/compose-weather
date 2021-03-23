package com.example.androiddevchallenge.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.HeavyRainAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.MostlyClearAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.RainAnimatableIcon
import com.example.androiddevchallenge.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.example.androiddevchallenge.data.WeatherBackground.ClearBg
import com.example.androiddevchallenge.data.WeatherBackground.CloudyBg
import com.example.androiddevchallenge.data.WeatherBackground.RainBg
import com.example.androiddevchallenge.data.WeatherBackground.ShowersBg
import com.example.androiddevchallenge.data.WeatherBackground.SunnyBg
import com.example.androiddevchallenge.data.WeatherComposedInfo.CloudyComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.CloudyRainComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.HeavyRainComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.MostlyClearComposed
import com.example.androiddevchallenge.data.WeatherComposedInfo.SunnyComposed
import com.example.androiddevchallenge.data.WeatherIcon.CloudyIcon
import com.example.androiddevchallenge.data.WeatherIcon.HeavyRainIcon
import com.example.androiddevchallenge.data.WeatherIcon.MostlyClearIcon
import com.example.androiddevchallenge.data.WeatherIcon.RainIcon
import com.example.androiddevchallenge.data.WeatherIcon.SunnyIcon
import com.example.androiddevchallenge.ui.ComposeInfo
import com.example.androiddevchallenge.ui.ComposedIcon
import com.example.androiddevchallenge.ui.IconInfo
import com.example.androiddevchallenge.ui.theme.teal500
import com.example.androiddevchallenge.ui.theme.teal700
import com.example.androiddevchallenge.ui.theme.teal900
import com.example.androiddevchallenge.ui.theme.yellow200
import com.example.androiddevchallenge.ui.theme.yellow500
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
    Sunny(
        "Sunny",
        SunnyComposed, SunnyIcon, SunnyAnimatableIcon, SunnyBg
    ),
    MostlyClear(
        "Clear with periodic clouds",
        MostlyClearComposed, MostlyClearIcon, MostlyClearAnimatableIcon, ClearBg
    ),

    Cloudy(
        "Cloudy",
        CloudyComposed, CloudyIcon, CloudyAnimatableIcon, CloudyBg
    ),

    CloudyRain(
        "Cloudy with periodic showers",
        CloudyRainComposed, RainIcon, RainAnimatableIcon, ShowersBg
    ),

    HeavyRain(
        "Heavy Rain",
        HeavyRainComposed, HeavyRainIcon, HeavyRainAnimatableIcon, RainBg
    )
}


/**
 * background color for each type of weather
 */
typealias BgColors = Triple<Color, Color, Color>

object WeatherBackground {

    val ShowersBg = BgColors(
        teal700,
        Color.LightGray,
        teal900
    )

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

    val SunnyBg = BgColors(
        yellow200,
        teal500,
        yellow500
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

    val SunnyComposed = ComposeInfo(
        sun = IconInfo(200.dp, 0.dp to 0.dp),
        cloud = IconInfo(200.dp, (-20).dp to 0.dp, 0f),
        lightCloud = IconInfo(100.dp, (-20).dp to 70.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
        lightRain = IconInfo(80.dp, 45.dp to 60.dp, 0f),
    )

    val MostlyClearComposed = ComposeInfo(
        sun = IconInfo(170.dp, 20.dp to 0.dp),
        cloud = IconInfo(200.dp, (-20).dp to 0.dp, 0f),
        lightCloud = IconInfo(80.dp, 35.dp to 75.dp, 1f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
        lightRain = IconInfo(80.dp, 45.dp to 60.dp, 0f),
    )

    val CloudyComposed = ComposeInfo(
        sun = IconInfo(20.dp, 150.dp to 40.dp, alpha = 0f),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        lightCloud = IconInfo(100.dp, 10.dp to 25.dp),
        rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
        lightRain = IconInfo(80.dp, 45.dp to 60.dp, alpha = 0f),
    )

    val CloudyRainComposed = ComposeInfo(
        sun = IconInfo(120.dp, 80.dp to 0.dp),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        lightCloud = IconInfo(100.dp, 0.dp to 0.dp, 0f),
        rains = IconInfo(80.dp, 45.dp to 60.dp, 0f),
        lightRain = IconInfo(80.dp, 45.dp to 60.dp, 1f),
    )

    val HeavyRainComposed = ComposeInfo(
        sun = IconInfo(20.dp, 150.dp to 40.dp, alpha = 0f),
        cloud = IconInfo(200.dp, 0.dp to 0.dp),
        lightCloud = IconInfo(100.dp, 10.dp to 25.dp),
        rains = IconInfo(80.dp, 45.dp to 60.dp, alpha = 1f),
        lightRain = IconInfo(80.dp, 45.dp to 60.dp, 0f),
    )
}


/**
 * used in chart or navigation bar
 */
object WeatherIcon {
    val SunnyIcon = @Composable {
        Sun(Modifier.size(40.dp))
    }

    val MostlyClearIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            Sun(
                Modifier
                    .size(40.dp)
                    .offset(3.dp)
            )
            Cloud(
                Modifier
                    .size(16.dp)
                    .offset(8.dp, 18.dp)
            )
        }
    }


    val CloudyIcon = @Composable {
        Cloud(
            Modifier
                .size(40.dp)
                .padding(3.dp)
        )

    }

    val RainIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            Rains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp), lightRain = true
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }

    val HeavyRainIcon = @Composable {
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
    val SunnyAnimatableIcon = @Composable {
        AnimatableSun(
            Modifier
                .size(40.dp)
                .padding(2.dp)
        )
    }

    val MostlyClearAnimatableIcon = @Composable {
        Box(
            Modifier
                .size(40.dp)
        ) {
            AnimatableSun(
                Modifier
                    .size(40.dp)
                    .offset(3.dp)
            )
            Cloud(
                Modifier
                    .size(16.dp)
                    .offset(8.dp, 18.dp)
            )
        }

    }

    val CloudyAnimatableIcon = @Composable {
        AnimatableCloud(
            Modifier
                .size(40.dp)
                .padding(3.dp),
            800
        )

    }

    val RainAnimatableIcon = @Composable {
        Box(Modifier.size(40.dp)) {
            AnimatableRains(
                Modifier
                    .size(25.dp)
                    .offset(5.dp, 8.dp), true
            )
            Cloud(
                Modifier
                    .size(30.dp)
                    .align(Alignment.TopCenter)
            )
        }
    }


    val HeavyRainAnimatableIcon = @Composable {
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
fun PreviewSunnyIcon() {
    Row {
        SunnyIcon()
        SunnyAnimatableIcon()
    }
}

@Preview
@Composable
fun PreviewMostlyClearIcon() {
    Row {
        MostlyClearIcon()
        MostlyClearAnimatableIcon()
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
    Row {
        RainIcon()
        RainAnimatableIcon()
    }
}

@Preview
@Composable
fun PreviewHeavyRainIcon() {
    Row {
        HeavyRainIcon()
        HeavyRainAnimatableIcon()
    }
}


@Preview
@Composable
fun PreviewCloudyRain() {
    ComposedIcon(composeInfo = CloudyRainComposed)
}


@Preview
@Composable
fun PreviewHeavyRain() {
    ComposedIcon(composeInfo = HeavyRainComposed)
}


@Preview
@Composable
fun PreviewSunny() {
    ComposedIcon(composeInfo = SunnyComposed)
}


@Preview
@Composable
fun PreviewCloudy() {
    ComposedIcon(composeInfo = CloudyComposed)
}


@Preview
@Composable
fun PreviewMostlyClear() {
    ComposedIcon(composeInfo = MostlyClearComposed)
}

