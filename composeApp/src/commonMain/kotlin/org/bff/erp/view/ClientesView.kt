package org.bff.erp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.model.dto.ClienteDto
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.viewModel.bindCadastroCliente
import org.bff.erp.viewModel.retornoStatus

var updatePage = MutableStateFlow(false)
var cliente = mutableStateOf(ClienteDto())

@Composable
fun clienteScreen() {
    adicionarCliente()
    atualizaPagina()
}

@Composable
fun adicionarCliente() {
    var errorMessage by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(start = 100.dp, end = 25.dp, top = 15.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.background(color = cardBackgroundColor)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = {
                    updatePage.value = true
                }) {
                Icon (
                    imageVector = Icons.Default.Clear,
                    contentDescription = "limpar Página",
                    modifier = Modifier
                        .size(15.dp)
                )
            }

            OutlinedTextField(
                value = cliente.value.nome,
                onValueChange = { cliente.value.nome = it },
                label = {
                    Text(
                        "Nome Cliente${if (cliente.value.nome.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = if (cliente.value.nome.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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

            OutlinedTextField(
                value = cliente.value.razaoSocial,
                onValueChange = { cliente.value.razaoSocial = it },
                label = {
                    Text(
                        "Razão Social",
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
                    value = cliente.value.cnpj,
                    onValueChange = { cliente.value.cnpj = it },
                    label = {
                        Text(
                            "CNPJ/CPF${if (cliente.value.cnpj.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = if (cliente.value.cnpj.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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
                                fontSize = 12.sp,
                                color = if (cliente.value.email.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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
                                fontSize = 12.sp,
                                color = if (cliente.value.telefone.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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
                                fontSize = 12.sp,
                                color = if (cliente.value.dataNascimento.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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
                    value = cliente.value.rg,
                    onValueChange = { cliente.value.rg = it },
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
                            fontSize = 12.sp,
                            color = if (cliente.value.endereco.isEmpty() && errorMessage.isNotEmpty()) Color.Red else Color.Black
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
                        .padding(start = 350.dp)
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
private fun observarRetornoStatus() {
    Text(
        modifier = Modifier
            .padding(start = 18.dp),
        text = verificarRetornoStatus()
    )
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
            cliente.cnpj.isNotEmpty() &&
            cliente.endereco.isNotEmpty() &&
            cliente.telefone.isNotEmpty() &&
            cliente.email.isNotEmpty() &&
            cliente.dataNascimento.isNotEmpty()
}

@Composable
private fun atualizaPagina() {
    if (updatePage.collectAsState().value) {
        cliente.value = ClienteDto()
        updatePage.value = false
    }
}

