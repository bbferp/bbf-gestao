package org.bff.erp.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.bff.erp.model.Cliente
import org.bff.erp.model.clienteToClienteDTO
import org.bff.erp.networking.getAllClientes
import org.bff.erp.networking.setUpdateCliente
import org.bff.erp.util.BaseApi.BASE
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.view.abrirClienteSelecionado
import org.bff.erp.view.abrirClientesCadastradosView
import org.bff.erp.view.clienteSelecionado
import org.bff.erp.view.getAllClientesList
import org.bff.erp.viewModel.*

var abrirConfiguracaoClienteSelecionado = MutableStateFlow(false)
var abrirImagemCliente = MutableStateFlow(false)
var abrirInfoEndereco = MutableStateFlow(false)
var showDialog = MutableStateFlow(false)

@Composable
fun clientesCadastrados() {
    fetchDataClientesCadastrados()
    if (abrirClientesCadastradosView.collectAsState().value) {
        abrirClienteSelecionado.value = false
        clienteSelecionado.value = Cliente()
        loadClientesCadastradosList()
    }

    if(abrirClienteSelecionado.collectAsState().value) {
        loadClienteCadastrado()
        InfoCliente()

    }
}

@Composable
fun InfoCliente() {
    Card (
        modifier = Modifier
        .padding(start = 700.dp, end = 25.dp, top = 390.dp)
    ) {
        Column(
            modifier = Modifier
                .background(cardBackgroundColor)
        ) {
            Row {
                OutlinedTextField(
                    value = enderecoDto.value.logradouro,
                    onValueChange = { enderecoDto.value.logradouro = it },
                    label = {
                        Text(
                            "Logradouro:",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(350.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = enderecoDto.value.localidade,
                    onValueChange = { enderecoDto.value.localidade = it },
                    label = {
                        Text(
                            "Localidade:",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 5.dp)
                        .height(60.dp)
                        .width(350.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }

            Row {
                OutlinedTextField(
                    value = enderecoDto.value.bairro,
                    onValueChange = { enderecoDto.value.bairro = it },
                    label = {
                        Text(
                            "Bairro",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(350.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )


                OutlinedTextField(
                    value = enderecoDto.value.complemento,
                    onValueChange = { enderecoDto.value.complemento = it },
                    label = {
                        Text(
                            "Complemento",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier.width(250.dp)
                        .padding(start = 8.dp, end = 5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }
            Row {
                OutlinedTextField(
                    value = enderecoDto.value.numero,
                    onValueChange = { enderecoDto.value.numero = it },
                    label = {
                        Text(
                            "Numero",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 5.dp)
                        .width(100.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = enderecoDto.value.cep,
                    onValueChange = { enderecoDto.value.cep = it },
                    label = {
                        Text(
                            "Cep",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(start = 8.dp, bottom = 5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    ),
                )
            }
        }
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
                        "Nome:",
                        modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                        style = TextStyle(
                            fontSize = 13.sp
                        )
                    )

                    Text(
                        text = cliente.nome,
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )

                    Text(
                        "Nome fantasia:",
                        modifier = Modifier.padding(start = 5.dp, top = 5.dp),
                        style = TextStyle(
                            fontSize = 13.sp
                        )
                    )

                    Text(
                        text = cliente.fantasia,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun loadClienteCadastrado() {
    clienteDto.value =  clienteToClienteDTO(clienteSelecionado.value)

    Row {
        Card(
            modifier = Modifier
                .width(700.dp)
                .padding(start = 100.dp, end = 25.dp, top = 105.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.background(color = cardBackgroundColor)
                    .padding(8.dp),
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            abrirConfiguracaoClienteSelecionado.value = !abrirConfiguracaoClienteSelecionado.value
                        },
                        ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "limpar Página",
                            modifier = Modifier
                                .size(15.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            if(clienteDto.value.temImagem) {
                                val imageUrl = "https://${usuarioLogado.value.nome}-${usuarioLogado.value.senha}.$BASE/imagens/${clienteDto.value.id}"
                                val windowFeatures = "width=600,height=400"
                                window.open(imageUrl, "_blank", windowFeatures)
                                abrirImagemCliente.value = false
                            } else {
                                showDialog.value = true
                            }
                        },
                        ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Imagem cliente",
                            modifier = Modifier
                                .size(15.dp)
                        )
                    }
                }

                OutlinedTextField(
                    modifier = Modifier.height(60.dp),
                    value = clienteDto.value.nome,
                    onValueChange = { clienteDto.value.nome = it },
                    label = {
                        Text(
                            "Nome:",
                            style = TextStyle(
                                fontSize = 13.sp
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 11.sp)
                )

                OutlinedTextField(
                    modifier = Modifier.height(60.dp),
                    value = clienteDto.value.fantasia,
                    onValueChange = { clienteDto.value.fantasia = it },
                    label = {
                        Text(
                            "Nome fantasia:",
                            style = TextStyle(
                                fontSize = 13.sp
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 11.sp)
                )

                OutlinedTextField(
                    modifier = Modifier.height(60.dp),
                    value = clienteDto.value.observacao,
                    onValueChange = { clienteDto.value.observacao = it },
                    label = {
                        Text(
                            "Observação:",
                            style = TextStyle(
                                fontSize = 13.sp
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 11.sp)
                )

                Row {
                    OutlinedTextField(
                        modifier = Modifier
                            .height(60.dp),
                        value = clienteDto.value.cnpj_cpf,
                        onValueChange = { clienteDto.value.cnpj_cpf = it },
                        label = {
                            Text(
                                "CNPJ/CPF:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )
                }

                Row {
                    OutlinedTextField(
                        modifier = Modifier.height(60.dp),
                        value = clienteDto.value.email,
                        onValueChange = { clienteDto.value.email = it },
                        label = {
                            Text(
                                "Email:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(60.dp),
                        value = clienteDto.value.dataNascimento,
                        onValueChange = { clienteDto.value.dataNascimento = it },
                        label = {
                            Text(
                                "Data de nascimento:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )
                }

                Row {
                    OutlinedTextField(
                        modifier = Modifier.height(60.dp),
                        value = clienteDto.value.rg_ie,
                        onValueChange = { clienteDto.value.rg_ie = it },
                        label = {
                            Text(
                                "Rg/Ie:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(60.dp),
                        value = clienteDto.value.telefone,
                        onValueChange = { clienteDto.value.telefone = it },
                        label = {
                            Text(
                                "Telefone:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )
                }


                Row {
                    OutlinedTextField(
                        modifier = Modifier.height(60.dp),
                        value = clienteDto.value.limiteCredito,
                        onValueChange = { clienteDto.value.limiteCredito = it },
                        label = {
                            Text(
                                "Limite de crédito:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(60.dp),
                        value = clienteDto.value.vendedor,
                        onValueChange = { clienteDto.value.vendedor = it },
                        label = {
                            Text(
                                "Vendedor:",
                                style = TextStyle(
                                    fontSize = 13.sp
                                )
                            )
                        },
                        textStyle = TextStyle(fontSize = 11.sp)
                    )
                }
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

                abrirConfiguracaoCliente()
            }
        }

        if (showDialog.collectAsState().value)
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Aviso") },
                text = { Text("Este cliente não possui uma imagem disponível.") },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        Text("OK")
                    }
                }
            )
    }
}

fun fetchDataClientesCadastrados() {
    CoroutineScope(Dispatchers.Main).launch {
        getAllClientesList.value = getAllClientes(usuarioLogado.value.nome, usuarioLogado.value.senha)
    }
}

@Composable
fun abrirConfiguracaoCliente() {
    if (abrirConfiguracaoClienteSelecionado.collectAsState().value) {

        Card(
            modifier = Modifier
                .width(350.dp)
                .padding(top = 50.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier.background(cardBackgroundColor)
            ) {
                IconButton(
                    onClick = {
                        deletarCliente(clienteSelecionado.value)
                        voltarHome()
                    }) {

                    Text(
                        text = "Deletar cliente",
                        style = TextStyle(fontSize = 12.sp)
                    )

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar cliente",
                        modifier = Modifier
                            .padding(start = 100.dp)
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                       atualizarCliente()
                        voltarHome()
                    }) {
                    Text(
                        text = "Atualizar cliente",
                        style = TextStyle(fontSize = 12.sp)
                    )

                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Salvar cliente",
                        modifier = Modifier
                            .padding(end = 100.dp)
                            .size(15.dp)
                    )
                }
            }
        }
    }
}

fun atualizarCliente() {
     convertDtoToCadastroCliente().let { cliente ->
         cliente.id = clienteDto.value.id

         getAllClientesList.value.filter { it.id != cliente.id }.toMutableList().let {
             it.add(cliente)
             enviarListaAtualizada(it)
         }
     }
}

fun deletarCliente(clienteSelecionado: Cliente) {
    getAllClientesList.value.filter { it.id != clienteSelecionado.id }.let {
        enviarListaAtualizada(it.toMutableList())
    }
}

fun enviarListaAtualizada(listaFiltrada: MutableList<Cliente>) {
    CoroutineScope(Dispatchers.Main).launch {
        setUpdateCliente(listaFiltrada)
    }
}

private fun voltarHome() {
    abrirConfiguracaoClienteSelecionado.value = false
    abrirClienteSelecionado.value = false
    abrirClientesCadastradosView.value = false
}