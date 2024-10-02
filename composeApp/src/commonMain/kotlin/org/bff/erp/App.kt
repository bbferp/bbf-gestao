package org.bff.erp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.util.DefaultColors.MyAppTheme
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.view.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.bff.erp.viewModel.usuarioValidado
import org.bff.erp.viewModel.validarUsuario

var itemMenuSelected = MutableStateFlow(0)

@Composable
@Preview
fun App() {
    MyAppTheme {
        if (usuarioValidado.collectAsState().value) {
            setupNavigation()
            navigationRail()
        } else {
            //loginScreen()
            setupNavigation()
            navigationRail()
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
    val items = listOf(
        "DashBoard", "Cadastrar", "Pedidos",
        "OS", "Vendas", "Financeiro",
        "Relatórios", "Faturamento"
    )
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Add,
        Icons.Default.Add,
        Icons.Default.Add,
        Icons.Default.Add,
        Icons.Default.Info,
        Icons.Default.Info,
        Icons.Default.Info,
    )

    val itemSelected by itemMenuSelected.collectAsState()

    NavigationRail(
        modifier = Modifier.width(80.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = {
                    Icon(
                        icons[index], contentDescription = null,
                    )
                },
                label = {
                    Text(
                        item
                    )
                        },
                selected = itemSelected == index,
                onClick = {
                    itemMenuSelected.value = index
                }
            )
        }
    }
}

@Composable
fun setupNavigation() {
    val itemSelected  = itemMenuSelected.collectAsState().value
    when (itemSelected) {
        0 -> dashBoardView()
        1 -> cadastrarSubMenu()
        2 -> pedidosScreen()
        3 -> ordemServicoScreen()
        4 -> vendasScreen()
        5 -> financeiroSubMenu()
        6 -> relatorioScreen()
        7 -> faturamentoScreen()
    }
}

@Composable
fun financeiroSubMenu() {
    val submenuItems = listOf("Fluxo de caixa", "Controle de pagamentos", "A pagar", "A receber")

    var selectedSubMenu by remember { mutableStateOf(-1) }

    Column(modifier = Modifier
        .width(200.dp)
        .padding(start = 75.dp, top = 180.dp)
    ) {
        submenuItems.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .width(150.dp)
                    .background(cardBackgroundColor)
                    .clickable { selectedSubMenu = index }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))
            }
            if (selectedSubMenu == index) {

            }
        }
    }
}

@Composable
fun cadastrarSubMenu() {
    val submenuItems = listOf("Clientes", "Produtos", "Fornecedores", "Vendedores")

    var selectedSubMenu by remember { mutableStateOf(-1) }

    Column(modifier = Modifier
        .width(200.dp)
        .padding(start = 75.dp, top = 90.dp)
    ) {
        submenuItems.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .width(150.dp)
                    .background(cardBackgroundColor)
                    .clickable { selectedSubMenu = index }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                 text = item,
                 fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))
            }

            if (selectedSubMenu == index) {

            }
        }
    }
}
