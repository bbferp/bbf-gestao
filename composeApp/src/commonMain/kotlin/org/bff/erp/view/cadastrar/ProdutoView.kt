package org.bff.erp.view.cadastrar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.viewModel.enviarProdutos

@Composable
fun produtosScreen() {
    adicionarProduto()
}

@Composable
fun adicionarProduto() {
    var nomeProduto by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .size(width = 320.dp, height = 320.dp)
            .padding(start = 100.dp)
    ) {
        Column(
            modifier = Modifier.background(color = cardBackgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { "" },
                label = { Text("Ambiente em desenvolvimento") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )
        }
    }
}