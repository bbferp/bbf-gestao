package org.bff.erp.view.cadastrar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.viewModel.setupCondicaoPagamento

var tipoSelecionado = MutableStateFlow("")
var formaSelecionada = MutableStateFlow("")
var ativa = MutableStateFlow(false)


@Composable
fun cadastrarCondPagamento() {
    var codigo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    val tiposCondicao = listOf("Parcelado", "Semanal", "Mensal", "À Vista")
    val formasPagamento = listOf("Crediário", "Cartão", "Cheque")


    Card(
        modifier = Modifier
            .padding(start = 100.dp, end = 25.dp, top = 25.dp)
            .width(650.dp)
            .height(350.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(color = cardBackgroundColor)

        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                text = "Cadastro condição de pagamento",
                style = TextStyle(
                    fontSize = 14.sp
                )
            )

            Row {
                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = {
                        Text(
                            "Código",
                            style = TextStyle(fontSize = 12.sp)

                        )
                    },

                    modifier = Modifier
                        .padding(start = 15.dp)
                        .height(60.dp)
                        .width(100.dp)
                )

                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = {
                        Text(
                            "Descrição",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(350.dp)
                        .padding(start = 30.dp)
                )

                Checkbox(checked = ativa.value, onCheckedChange = { ativa.value = it })
                Text(
                    modifier = Modifier
                        .padding(top = 15.dp),
                    style = TextStyle(
                        fontSize = 13.sp
                    ),
                    text = "Ativa"
                )
            }

            Column(modifier = Modifier.padding(start = 15.dp, top = 25.dp)) {
                Row {
                    Text(
                        text = "Tipos de Condição:",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                    tiposCondicao.forEach { tipo ->
                        RadioButton(
                            selected = tipoSelecionado.value == tipo,
                            onClick = { tipoSelecionado.value = tipo }
                        )
                        Text(
                            text = tipo,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(start = 100.dp, top = 35.dp)) {
                Row {
                    Text(
                        text = "Forma de Pagamento:",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                    formasPagamento.forEach { forma ->
                        RadioButton(
                            selected = formaSelecionada.value == forma,
                            onClick = { formaSelecionada.value = forma }
                        )
                        Text(
                            text = forma,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
              salvarCondPag()
            },
            modifier = Modifier
                .padding(start = 500.dp, top = 300.dp, end = 15.dp, bottom = 10.dp)
                .height(80.dp)
                .width(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
        ) {
            Text(text = "Salvar", color = Color.White)
        }
    }
}

fun salvarCondPag() {
 setupCondicaoPagamento()
}

