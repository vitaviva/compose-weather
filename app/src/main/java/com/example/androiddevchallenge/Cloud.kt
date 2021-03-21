package com.example.androiddevchallenge

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun AnimatableCloud(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {

        val transition = rememberInfiniteTransition()

        val animateTween by transition.animateFloat(
            initialValue = -1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(1500, easing = LinearEasing),
                RepeatMode.Reverse
            )
        )

        Cloud(modifier = modifier.offset(5.dp * animateTween))
    }

}

@Composable
fun Cloud(modifier: Modifier = Modifier) {

    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_cloud),
        contentDescription = "",
        modifier = modifier
    )

}


@Preview
@Composable
fun previewCloud() {
    AnimatableCloud()
}


