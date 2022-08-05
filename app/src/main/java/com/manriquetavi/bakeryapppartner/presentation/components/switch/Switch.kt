package com.manriquetavi.bakeryapppartner.presentation.components.switch

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun Switch(
    scale: Float = 2f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    thumbColor: Color = Color.White,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true)
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move the thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            }
    ) {

        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx())
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )

    }

    Spacer(modifier = Modifier.height(18.dp))

    Text(text = if (switchON.value) "ON" else "OFF")
}