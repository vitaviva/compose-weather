package com.example.androiddevchallenge

import android.animation.TypeConverter
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun WeatherView() {

//    val temp = animateFloatAsState(targetValue = 1f)

    val (cur, setCur) = remember { mutableStateOf(sunny) }
    val (new, setNew) = remember { mutableStateOf(sunny) }
    var trigger by remember { mutableStateOf(0f) }

    val tmp by animateFloatAsState(
        targetValue = trigger,
        finishedListener = {
            setCur(new)
            trigger = 0f
        }, animationSpec = tween(1000)
    )

    val composeInfo = remember(tmp) {
        cur + (new - cur) * tmp
    }

    Column(
        modifier = Modifier
            .width(800.dp)
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color.LightGray,
                        Color.Gray,
                        Color.White
                    )
                )
            )
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            ComposedIcon(
                Modifier.align(Alignment.Center),
                composeInfo
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                "36C", style = TextStyle(
                    fontSize = 80.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Row(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.4f))
        ) {
            Text("am")
        }

        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            (0..2).forEach {
                Text(
                    text = "$it",
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            setNew(sunrain)
                            trigger = 1f
                        },
                    textAlign = TextAlign.Center
                )
            }

            (3..6).forEach {
                Text(
                    text = "$it",
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            setNew(sunny)
                            trigger = 1f
                        },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}