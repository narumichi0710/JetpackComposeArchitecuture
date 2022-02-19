package com.narumichi.presentation.layout.core.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomTypography internal constructor(
    //example
    val appBar: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        color = CustomColors.brand
    )

)