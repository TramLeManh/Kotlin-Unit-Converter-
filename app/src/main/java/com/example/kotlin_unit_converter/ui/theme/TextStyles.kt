package com.example.kotlin_unit_converter.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val h1 = TextStyle(
    fontWeight = FontWeight.Bold,
    letterSpacing = 1.5.sp,
    fontSize = 20.sp,
    shadow = Shadow(
        color = Color.Gray,
        offset = Offset(15f, 2f),
        blurRadius = 10f
    ),
    )
val h2 = TextStyle(
    fontWeight = FontWeight.Bold,
    color = LightRed,
    letterSpacing = 0.5.sp,
    fontSize = 20.sp
)