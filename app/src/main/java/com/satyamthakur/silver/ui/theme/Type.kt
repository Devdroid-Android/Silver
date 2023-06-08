package com.satyamthakur.silver.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.satyamthakur.silver.R

// Set of Material typography styles to start with
val MerryWeather = FontFamily(
    Font(R.font.merriweather_light, FontWeight.ExtraLight),
    Font(R.font.merriweather_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.merriweather_light, FontWeight.Light),
    Font(R.font.merriweather_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.merriweather_regular, FontWeight.Normal),
    Font(R.font.merriweather_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.merriweather_bold, FontWeight.Bold),
    Font(R.font.merriweather_bold, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.merriweather_bold, FontWeight.ExtraBold),
    Font(R.font.merriweather_bold, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.merriweather_black, FontWeight.Black),
    Font(R.font.merriweather_black, FontWeight.Black, FontStyle.Italic)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
