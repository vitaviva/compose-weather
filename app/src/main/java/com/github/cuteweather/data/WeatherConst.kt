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
package com.github.cuteweather.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.cuteweather.data.WeatherAnimatableIcon.CloudyAnimatableIcon
import com.github.cuteweather.data.WeatherAnimatableIcon.HeavyRainAnimatableIcon
import com.github.cuteweather.data.WeatherAnimatableIcon.MostlyClearAnimatableIcon
import com.github.cuteweather.data.WeatherAnimatableIcon.RainAnimatableIcon
import com.github.cuteweather.data.WeatherAnimatableIcon.SunnyAnimatableIcon
import com.github.cuteweather.data.WeatherBackground.ClearBg
import com.github.cuteweather.data.WeatherBackground.CloudyBg
import com.github.cuteweather.data.WeatherBackground.RainBg
import com.github.cuteweather.data.WeatherBackground.ShowersBg
import com.github.cuteweather.data.WeatherBackground.SunnyBg
import com.github.cuteweather.data.WeatherComposedInfo.CloudyComposed
import com.github.cuteweather.data.WeatherComposedInfo.CloudyRainComposed
import com.github.cuteweather.data.WeatherComposedInfo.HeavyRainComposed
import com.github.cuteweather.data.WeatherComposedInfo.MostlyClearComposed
import com.github.cuteweather.data.WeatherComposedInfo.SunnyComposed
import com.github.cuteweather.data.WeatherIcon.CloudyIcon
import com.github.cuteweather.data.WeatherIcon.HeavyRainIcon
import com.github.cuteweather.data.WeatherIcon.MostlyClearIcon
import com.github.cuteweather.data.WeatherIcon.RainIcon
import com.github.cuteweather.data.WeatherIcon.SunnyIcon
import com.github.cuteweather.ui.ComposeInfo
import com.github.cuteweather.ui.ComposedIcon
import com.github.cuteweather.ui.IconInfo
import com.github.cuteweather.ui.theme.teal500
import com.github.cuteweather.ui.theme.teal700
import com.github.cuteweather.ui.theme.teal900
import com.github.cuteweather.ui.theme.yellow200
import com.github.cuteweather.ui.theme.yellow500
import com.github.cuteweather.ui.weathericon.AnimatableCloud
import com.github.cuteweather.ui.weathericon.AnimatableRains
import com.github.cuteweather.ui.weathericon.AnimatableSun
import com.github.cuteweather.ui.weathericon.Cloud
import com.github.cuteweather.ui.weathericon.Rains
import com.github.cuteweather.ui.weathericon.Sun

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
        "Heavy rain",
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

    val IconSize = 200.dp // ComposedIcon size

    val SunnyComposed = ComposeInfo(
        sun = IconInfo(1f),
        cloud = IconInfo(0.8f, Offset(-0.1f, 0.1f), 0f),
        lightCloud = IconInfo(0.5f, Offset(-0.15f, 0.35f), 0f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
    )

    val MostlyClearComposed = ComposeInfo(
        sun = IconInfo(0.85f, Offset(0.1f, 0f)),
        cloud = IconInfo(0.5f, Offset(-0.1f, 0.1f), 0f),
        lightCloud = IconInfo(0.4f, Offset(0.175f, 0.375f), 1f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
    )

    val CloudyComposed = ComposeInfo(
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(0.05f, 0.125f)),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 0f),
    )

    val CloudyRainComposed = ComposeInfo(
        sun = IconInfo(0.6f, Offset(0.4f, 0f)),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(-0.15f, 0.125f), 0f),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 1f),
    )

    val HeavyRainComposed = ComposeInfo(
        sun = IconInfo(0.1f, Offset(0.75f, 0.2f), alpha = 0f),
        cloud = IconInfo(0.8f, Offset(0.1f, 0.1f)),
        lightCloud = IconInfo(0.5f, Offset(0.05f, 0.125f)),
        rains = IconInfo(0.4f, Offset(0.225f, 0.3f), alpha = 1f),
        lightRain = IconInfo(0.4f, Offset(0.225f, 0.3f), 0f),
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
                    .offset(5.dp, 8.dp),
                lightRain = true
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
                    .offset(5.dp, 8.dp),
                true
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

@Preview(heightDp = 200)
@Composable
fun PreviewIcon() {
    Column {
        Row(Modifier.weight(1f)) {
            SunnyIcon()
            SunnyAnimatableIcon()
        }
        Row(Modifier.weight(1f)) {
            MostlyClearIcon()
            MostlyClearAnimatableIcon()
        }
        Row(Modifier.weight(1f)) {
            CloudyIcon()
            CloudyAnimatableIcon()
        }
        Row(Modifier.weight(1f)) {
            RainIcon()
            RainAnimatableIcon()
        }
        Row(Modifier.weight(1f)) {
            HeavyRainIcon()
            HeavyRainAnimatableIcon()
        }
    }
}

@Preview(widthDp = 740, heightDp = 480)
@Composable
fun PreviewComposedIcon() {
    Column(
        Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {

        val modifier = Modifier
            .padding(2.5.dp)
            .background(teal700)
            .padding(5.dp)

        Row(Modifier.align(Alignment.CenterHorizontally)) {
            ComposedIcon(
                modifier, SunnyComposed
            )
            ComposedIcon(
                modifier, CloudyComposed
            )
            ComposedIcon(
                modifier, CloudyRainComposed
            )
        }
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            ComposedIcon(
                modifier, HeavyRainComposed
            )
            ComposedIcon(
                modifier, MostlyClearComposed
            )
        }
    }
}
