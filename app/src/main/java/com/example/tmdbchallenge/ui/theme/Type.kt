package com.example.tmdbchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tmdbchallenge.R

// TODO: Verify font family
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W400,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.25.sp,
        color = Color(0xFFFBFAFE)
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        color = Color(0xFFFBFAFE)
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = Color(0xFF6B6B83)
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 22.77.sp,
        color = Color.White
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = Color(0xFF6B6B83)
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        color = Color.White
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 30.sp,
        color = Color.White
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 22.77.sp,
        color = Color.White
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = Color(0xFF6B6B83)
    )
)