package com.narumichi.presentation.layout.core.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object CustomColors {
    //example
    val brand = Color(android.graphics.Color.parseColor("#0074C2"))

}

interface CustomColorPalette {
    val brand: Color
    val materialColors: Colors
}

fun lightColorPalette(): CustomColorPalette = object : CustomColorPalette {
    override val brand: Color = CustomColors.brand
    override val materialColors: Colors = lightColors(
        //TODO:カラーを指定
    )
}