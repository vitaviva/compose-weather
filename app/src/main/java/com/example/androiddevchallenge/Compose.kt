package com.example.androiddevchallenge

import Cloud
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun compose2() {
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


@Preview
@Composable
fun compose(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(300.dp)) {

        Sun(
            modifier = Modifier
                .size(180.dp)
                .offset(90.dp, 0.dp)
        )

        Rains(
            Modifier
                .size(120.dp)
                .offset(45.dp, 80.dp)
        )


        Cloud(
            modifier = Modifier
                .scale(1.1f)
                .offset(0.dp, 30.dp)
        )
    }
}