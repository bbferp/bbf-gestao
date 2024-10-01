package org.bff.erp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "bbf-gestao",
    ) {
        App()
    }
}