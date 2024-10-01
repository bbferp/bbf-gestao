package org.bff.erp

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.bff.erp.view.produtosScreen

var itemMenuSelected = MutableStateFlow(0)


@Composable
@Preview
fun App() {
    MaterialTheme {
        setupNavigation()
        navigationRail()
    }
}

@Composable
fun navigationRail() {
    val items = listOf("Produtos")
    val icons = listOf(
        Icons.Filled.Edit,

        )
    val itemSelected by itemMenuSelected.collectAsState()

    NavigationRail(
        modifier = Modifier.width(72.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(icons[index], contentDescription = null) },
                label = { Text(item) },
                selected = itemSelected == index,
                onClick = { itemMenuSelected.value = index
                }
            )
        }
    }
}

@Composable
fun setupNavigation() {
    val itemSelected  = itemMenuSelected.collectAsState().value
    when (itemSelected) {
        0 -> produtosScreen()
    }
}
