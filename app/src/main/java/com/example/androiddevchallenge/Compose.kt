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
fun compose() {
    Box(modifier = Modifier.size(width = 300.dp, height = 300.dp)) {

        Sun(
            modifier = Modifier
                .scale(0.9f)
                .offset(90.dp, 0.dp)
        )

        Rains2(
            Modifier
                .scale(0.4f)
                .offset(-100.dp, 240.dp))
//        Cloud(
//            modifier = Modifier
//                .scale(1.1f)
//                .offset(0.dp, 50.dp)
//        )
    }
}



//@Preview
//@Composable
//fun compose2() {
//    Box(modifier = Modifier.size(width = 200.dp, height = 200.dp)) {
//
//        Sun(
//            modifier = Modifier
//                .scale(0.5f)
//                .offset(30.dp, (-22).dp)
//        )
//
//        Rains(
//            Modifier
//                .offset(-10.dp, 30.dp)
//                .size(100.dp))
//
//
//        Cloud(
//            modifier = Modifier
//                .scale(1.1f)
//                .offset(0.dp, 20.dp)
//        )
//    }
//}