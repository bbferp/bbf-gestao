package org.bff.erp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.bff.erp.view.produtosScreen
import org.bff.erp.viewModel.usuarioValidado
import org.bff.erp.viewModel.validarUsuario

var itemMenuSelected = MutableStateFlow(0)

@Composable
@Preview
fun App() {


    MaterialTheme {
        if (usuarioValidado.collectAsState().value) {
            println("retornou true")
            setupNavigation()
            navigationRail()
        } else {
            loginScreen()
        }
    }
}

@Composable
fun loginScreen() {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(backgroundColor)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 320.dp, height = 320.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.background(color = cardBackgroundColor)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuário") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                Button(
                    onClick = {
                       validarUsuario(usuario,senha)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                ) {
                    Text(text = "Entrar", color = Color.White)
                }
            }
        }
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
