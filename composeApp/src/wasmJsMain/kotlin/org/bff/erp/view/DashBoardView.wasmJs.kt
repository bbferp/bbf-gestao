package org.bff.erp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.model.Cliente
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.view.dashboard.clientesCadastrados

var abrirClientesCadastradosView = MutableStateFlow(false)
var abrirClienteSelecionado = MutableStateFlow(false)
var getAllClientesList = MutableStateFlow<MutableList<Cliente>>(mutableListOf())
var clienteSelecionado = MutableStateFlow(Cliente())


@Composable
actual fun dashBoardView() {
    loadToolbarView()
    clientesCadastrados()
}

@Composable
fun loadToolbarView() {
    Row{
        Card(
            modifier = Modifier
                .padding(start = 40.dp, bottom = 100.dp)
                .width(450.dp)
                .height(100.dp)
                .offset(y = (-40).dp),
            shape = RoundedCornerShape(48.dp)
        ) {
            iconClientesCadastrados(onClick = { abrirClientesCadastradosView.value = !abrirClientesCadastradosView.value})
        }
    }
}

@Composable
fun iconClientesCadastrados(onClick: () -> Unit) {
    Row(modifier = Modifier
        .background(cardBackgroundColor)
    ) {
        IconButton(onClick = onClick,
            Modifier.padding(start = 50.dp, top = 60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Clientes cadastrados",
                tint = backgroundColor
            )
        }

        Text(
            text = "Clientes cadastrados",
            modifier = Modifier.padding(top = 73.dp),
            style = TextStyle(
                color = backgroundColor,
                fontSize = 12.sp
            )
        )
    }
}


