package org.bff.erp.view.cadastrar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.itemCadastrarSubMenu
import org.bff.erp.itemMenuSelected
import org.bff.erp.model.dto.ClienteDto
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.viewModel.bindCadastroCliente
import org.bff.erp.viewModel.retornoStatus
import selecionarImage

var limparCampos = MutableStateFlow(false)
var cliente = mutableStateOf(ClienteDto())
var abrirControleCreditoView = MutableStateFlow(false)
var abrirCadastroImagemView = MutableStateFlow(false)
var abrirCadastroAromas = MutableStateFlow(false)

@Composable
actual fun clienteScreen() {
    adicionarCliente()
    limparPagina()
    abrirControleCredito()
    setupImagem()
    aromasView()
}

@Composable
fun adicionarCliente() {
    var errorMessage by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(start = 100.dp, end = 25.dp, top = 105.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.background(color = cardBackgroundColor)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                IconButton(
                    onClick = {
                        voltarHome()
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        modifier = Modifier
                            .size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        limparCampos.value = true
                    },

                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "limpar Página",
                        modifier = Modifier
                            .size(15.dp)
                    )
                }
            }

            OutlinedTextField(
                value = cliente.value.nome,
                onValueChange = { cliente.value.nome = it },
                label = {
                    Text(
                        "Nome Cliente/RazãoSocial${if (cliente.value.nome.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )

            OutlinedTextField(
                value = cliente.value.nomeFantasia,
                onValueChange = { cliente.value.nomeFantasia = it },
                label = {
                    Text(
                        "Nome Fantasia",
                        style = TextStyle(fontSize = 12.sp)
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )

            Row {
                OutlinedTextField(
                    value = cliente.value.cnpj_cpf,
                    onValueChange = { cliente.value.cnpj_cpf = it },
                    label = {
                        Text(
                            "CNPJ/CPF${if (cliente.value.cnpj_cpf.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = cliente.value.email,
                    onValueChange = { cliente.value.email = it },
                    label = {
                        Text(
                            "E-mail${if (cliente.value.email.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(250.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = cliente.value.telefone,
                    onValueChange = { cliente.value.telefone = it },
                    label = {
                        Text(
                            "Telefone${if (cliente.value.telefone.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(250.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = cliente.value.dataNascimento,
                    onValueChange = { cliente.value.dataNascimento = it },
                    label = {
                        Text(
                            "Data de Nascimento${if (cliente.value.dataNascimento.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(200.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = cliente.value.rg_ie,
                    onValueChange = { cliente.value.rg_ie = it },
                    label = {
                        Text(
                            "RG/IE",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(250.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }

            OutlinedTextField(
                value = cliente.value.endereco,
                onValueChange = { cliente.value.endereco = it },
                label = {
                    Text(
                        "Endereço${if (cliente.value.endereco.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )

            OutlinedTextField(
                value = cliente.value.observacao,
                onValueChange = { cliente.value.observacao = it },
                label = {
                    Text(
                        "Observação",
                        style = TextStyle(fontSize = 12.sp)
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }

            Row {
                iconControleCredito(onClick = { abrirControleCreditoView.value = !abrirControleCreditoView.value })
                iconImagem(onClick = {abrirCadastroImagemView.value = !abrirControleCreditoView.value})
                iconCadastroAroma(onClick = {abrirCadastroAromas.value = !abrirCadastroAromas.value})

                Button(
                    onClick = {
                        errorMessage = ""
                        if (validarCampos(cliente.value)) {
                            bindCadastroCliente(cliente.value)
                        } else {
                            errorMessage = "Por favor, preencha todos os campos obrigatórios."
                        }
                    },
                    modifier = Modifier
                        .padding(start = 100.dp, top = 10.dp)
                        .height(35.dp)
                        .width(250.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                ) {
                    Text(text = "Cadastrar", color = Color.White)
                }
                observarRetornoStatus()
            }
        }
    }
}

@Composable
fun iconCadastroAroma(onClick: () -> Unit) {
    Row(modifier = Modifier
        .padding(8.dp)) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Cadastrar Aromas"
            )
        }

        Text(
            text = "Cadastrar Aromas",
            modifier = Modifier.padding(
                start = 8.dp, top = 16.dp
            ),
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Composable
fun iconImagem(onClick: () -> Unit) {
    Row(modifier = Modifier
        .padding(8.dp)) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Cadastrar imagem"
            )
        }

        Text(
            text = "Selecionar Imagem",
            modifier = Modifier.padding(
                start = 8.dp, top = 16.dp
            ),
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Composable
fun setupImagem() {
    if(abrirCadastroImagemView.collectAsState().value) {
        selecionarImage()
        abrirCadastroImagemView.value = false
    }
}

@Composable
private fun observarRetornoStatus() {
    Text(
        modifier = Modifier
            .padding(start = 18.dp),
        text = verificarRetornoStatus()
    )
}

@Composable
private fun iconControleCredito(onClick: () -> Unit) {
    Row(modifier = Modifier
        .padding(8.dp)) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Controle de Crédito"
            )
        }

        Text(
            text = "Controle de Crédito",
            modifier = Modifier.padding(
                start = 8.dp, top = 16.dp
            ),
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Composable
fun abrirControleCredito() {
    if (abrirControleCreditoView.collectAsState().value) {
        Card(
            modifier = Modifier
                .padding(96.dp, bottom = 5.dp, top = 35.dp)
                .width(290.dp)

            ,
            elevation = 4.dp
        ) {
            Column(modifier = Modifier
                .background(cardBackgroundColor)
                .padding(16.dp)) {

                fecharControleCredito()
                Text(text = "Forma Autorizada", fontWeight = FontWeight.Bold)

                val formaAutorizada = remember { mutableStateOf(setOf<String>()) }
                cliente.value.formaAutorizada = formaAutorizada.value

                val formas = listOf("Crediário", "Cheque", "Administradora")
                formas.forEach { forma ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = forma in formaAutorizada.value,
                            onCheckedChange = {
                                formaAutorizada.value = if (it) {
                                    formaAutorizada.value + forma
                                } else {
                                    formaAutorizada.value - forma
                                }
                            },
                            colors =  CheckboxDefaults.colors(
                                checkedColor = backgroundColor,
                                uncheckedColor = Color.Gray
                            )
                        )
                        Text(text = forma)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Tipo de Venda", fontWeight = FontWeight.Bold)

                val tipoVenda = remember { mutableStateOf(setOf<String>()) }
                cliente.value.tipoVenda = tipoVenda.value

                val tipos = listOf("Atacado", "Varejo", "Ambos")
                tipos.forEach { tipo ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = tipo in tipoVenda.value,
                            onCheckedChange = {
                                tipoVenda.value = if (it) {
                                    tipoVenda.value + tipo
                                } else {
                                    tipoVenda.value - tipo
                                }
                            },
                            colors =  CheckboxDefaults.colors(
                                checkedColor = backgroundColor,
                                uncheckedColor = Color.Gray
                            )
                        )
                        Text(text = tipo)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = cliente.value.limiteCredito,
                    onValueChange = { cliente.value.limiteCredito = it },
                    label = {
                        Text(
                            "Limite de crédito",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },

                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.width(250.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }
        }
    }
}

@Composable
fun fecharControleCredito() {
    IconButton(onClick = {
        abrirControleCreditoView.value = !abrirControleCreditoView.value
    },
        Modifier
            .padding(start = 240.dp)

    ) {
       Icon(
           Icons.Default.Close,
           "fechar controle de crédito"
       )
    }
}

@Composable
private fun verificarRetornoStatus(): String {
    return  when (retornoStatus.collectAsState().value) {
        200 -> "Cadastro realizado com sucesso"
        400 -> "Falha ao realizar o cadastro"
        else -> ""
    }
}

private fun validarCampos(cliente: ClienteDto): Boolean {
    return cliente.nome.isNotEmpty() &&
            cliente.rg_ie.isNotEmpty() &&
            cliente.endereco.isNotEmpty() &&
            cliente.telefone.isNotEmpty() &&
            cliente.email.isNotEmpty() &&
            cliente.dataNascimento.isNotEmpty()
}

@Composable
private fun limparPagina() {
    if (limparCampos.collectAsState().value) {
        cliente.value = ClienteDto()
        limparCampos.value = false
        retornoStatus.value = 0
    }
}

@Composable
private fun aromasView() {
    if(abrirCadastroAromas.collectAsState().value){
        val tipo  = "fixa"
        Card(
            modifier = Modifier
                .padding(start = 240.dp, top = 150.dp)
                .width(350.dp)

        ) {
            Column(
                modifier = Modifier.background(cardBackgroundColor)
            ){
                OutlinedTextField(
                    value = cliente.value.limiteCredito,
                    onValueChange = { cliente.value.limiteCredito = it },
                    label = {
                        Text(
                            "Nome Aroma",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .padding(start = 20.dp, top = 15.dp)
                        .width(250.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                Row {
                    RadioButton(
                        selected = tipoSelecionado.value == tipo,
                        onClick = { tipoSelecionado.value = tipo }
                    )
                    Text(
                        modifier = Modifier,
                        text = tipo,
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}



private fun voltarHome() {
    itemMenuSelected.value = 0
    itemCadastrarSubMenu.value = -1
}


