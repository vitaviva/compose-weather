package com.example.androiddevchallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WeatherView() {


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
        Row {

            Text("36C", modifier = Modifier.weight(1f))

            compose()
        }

        Row(Modifier.weight(1f)) {
            Text("am")
        }

        Row(modifier = Modifier.height(100.dp)) {
            (0..6).forEach {
                Text(
                    text = "$it",
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}