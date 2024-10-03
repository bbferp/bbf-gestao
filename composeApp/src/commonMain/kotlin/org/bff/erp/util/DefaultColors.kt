package org.bff.erp.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object DefaultColors {

    val backgroundColor = Color(0xff3a597b)
    val backgroundColum = Color(0xff304A66)
    val backgroundDash = Color(0xffF7F7F7)
    val cardBackgroundColor = Color(0xffDEDEDE)
    var corFaturamento = Color(0xff01FA92)
    var corDespesas = Color(0xffFA8989)

    private val LightColorPalette = lightColors(
        primary = Color(0xff3a597b),
        background = backgroundColor,
        primaryVariant = Color(0xff3a597b),
    )

    private val DarkColorPalette = darkColors(
        primary = Color(0xff3a597b),
        primaryVariant = Color(0xff3a597b)
    )

    @Composable
    fun MyAppTheme(
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colors = LightColorPalette,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    content()
                }
            }
        )
    }

}