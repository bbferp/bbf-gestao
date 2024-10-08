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
                    value = nomeProduto,
                    onValueChange = { nomeProduto = it },
                    label = { Text("Nome Produto") },
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
                     enviarProdutos(nomeProduto)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                ) {
                    Text(text = "Cadastrar", color = Color.White)
                }
            }
        }
    }
}