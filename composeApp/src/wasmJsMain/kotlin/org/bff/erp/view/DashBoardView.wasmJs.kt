package org.bff.erp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.bff.erp.model.Cliente
import org.bff.erp.networking.getAllClientes
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor


var abrirClientesCadastradosView = MutableStateFlow(false)
var abrirClienteSelecionado = MutableStateFlow(false)
var getAllClientesList = MutableStateFlow<MutableList<Cliente>>(mutableListOf())
var clienteSelecionado = MutableStateFlow(Cliente())


@Composable
actual fun dashBoardView() {
    loadToolbarView()
    fetchDataClientesCadastrados()

    if (abrirClientesCadastradosView.collectAsState().value) {
        abrirClienteSelecionado.value = false
        clienteSelecionado.value = Cliente()
        loadClientesCadastradosList()
    }

    if(abrirClienteSelecionado.collectAsState().value) {
        loadClienteCadastrado()
    }
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun loadClientesCadastradosList() {
    val listAllClientes by rememberUpdatedState(newValue = getAllClientesList.collectAsState().value)

    FlowRow(
        modifier = Modifier
            .padding(top = 100.dp, start = 100.dp)
    ) {
        listAllClientes.forEach { cliente ->
            Card(
                elevation = 8.dp,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 10.dp, end = 50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        clienteSelecionado.value = cliente
                        abrirClienteSelecionado.value = true
                        abrirClientesCadastradosView.value = false
                    }
            ) {
                Column(modifier = Modifier.background(cardBackgroundColor)
                ) {
                    Text(
                        color = backgroundColor,
                        text = cliente.nome,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                    Text(
                        color = backgroundColor,
                        text = "Nome fantasia: ${cliente.nomeFantasia}",
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun loadClienteCadastrado() {
    Row {
        Card(
            modifier = Modifier
                .width(450.dp)
                .padding(start = 100.dp, end = 25.dp, top = 105.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.background(color = cardBackgroundColor)
                    .padding(8.dp),
            ) {
                Text(
                    "Nome:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.nome,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Nome fantasia:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.nomeFantasia,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Razao social:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.razaoSocial,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "CNPJ:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.cnpj,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Endereco:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.endereco,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Email:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.email,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Data nascimento:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.dataNascimento,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Rg/Ie:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.rg,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Telefone:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.telefone,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Observação:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.observacao,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Limite de crédito:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.limiteCredito,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Vendedor:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.vendedor,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .width(450.dp)
                .padding(start = 100.dp, end = 25.dp, top = 105.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.background(color = cardBackgroundColor)
                    .padding(8.dp),
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Tipo venda:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.tipoVenda.joinToString(","),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Formas autorizadas:",
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )

                Text(
                    clienteSelecionado.value.formaAutorizada.joinToString(","),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

fun fetchDataClientesCadastrados() {
    CoroutineScope(Dispatchers.Main).launch {
        getAllClientesList.value = getAllClientes("aromas", "01")
    }
}
