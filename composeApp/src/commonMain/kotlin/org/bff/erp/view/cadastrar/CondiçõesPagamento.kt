import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor

@Composable
fun cadastrarCondPagamento() {
    var codigo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var ativa by remember { mutableStateOf(false) }
    val tiposCondicao = listOf("Parcelado", "Semanal", "Mensal", "À Vista")
    val formasPagamento = listOf("Crediário", "Cartão", "Cheque")

    val tiposSelecionados by remember { mutableStateOf(mutableSetOf<String>()) }
    val formasSelecionadas by remember { mutableStateOf(mutableSetOf<String>()) }

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
                text = "Cadastro condição de pagamento", style = MaterialTheme.typography.h6
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
                        .width(350.dp)
                        .padding(start = 30.dp)
                )

                Checkbox(checked = ativa, onCheckedChange = { ativa = it })
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Ativa"
                )
            }

            Column(modifier = Modifier.padding(start = 15.dp, top = 25.dp)) {
                Row {
                    Text(
                        text = "Tipos de Condição:",
                    )

                    tiposCondicao.forEach { tipo ->
                        Checkbox(
                            modifier = Modifier.padding(2.dp),
                            checked = tiposSelecionados.contains(tipo),
                            onCheckedChange = {
                                if (it) {
                                    tiposSelecionados.add(tipo)
                                } else {
                                    tiposSelecionados.remove(tipo)
                                }
                            }
                        )
                        Text(tipo)
                    }
                }
            }

            Column(modifier = Modifier.padding(start = 100.dp, top = 35.dp)) {
                Row {
                    Text(
                        text = "Forma de Pagamento:",
                    )

                    formasPagamento.forEach { forma ->
                        Checkbox(
                            modifier = Modifier
                                .padding(2.dp),
                            checked = formasSelecionadas.contains(forma),
                            onCheckedChange = {
                                if (it) {
                                    formasSelecionadas.add(forma)
                                } else {
                                    formasSelecionadas.remove(forma)
                                }
                            }
                        )
                        Text(forma)
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
                .width(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
        ) {
            Text(text = "Salvar", color = Color.White)
        }
    }
}

fun salvarCondPag() {
}

