package org.bff.erp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState(
        width = 1250.dp,
        height = 720.dp
    )
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "bbf-gestao"
    ) {
        App()
    }
}