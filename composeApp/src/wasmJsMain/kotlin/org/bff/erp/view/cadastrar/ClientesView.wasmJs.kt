package org.bff.erp.view.cadastrar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bff.erp.itemCadastrarSubMenu
import org.bff.erp.itemMenuSelected
import org.bff.erp.model.ClienteDto
import org.bff.erp.model.EnderecoDto
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.util.Format.formatCnpj
import org.bff.erp.util.Format.formatCpf
import org.bff.erp.viewModel.*
import selecionarImage


var limparCampos = MutableStateFlow(false)
var abrirControleCreditoView = MutableStateFlow(false)
var abrirCadastroImagemView = MutableStateFlow(false)
var abrirCadastroAromas = MutableStateFlow(false)
var cnpjValue = mutableStateOf(TextFieldValue(""))
var cpfValue = mutableStateOf(TextFieldValue(""))

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


    val focusRequesterNome = remember { FocusRequester() }
    val focusRequesterNomeFantasia = remember { FocusRequester() }
    val cpf = remember { FocusRequester() }
    val cnpj = remember { FocusRequester() }
    val logradouro = remember { FocusRequester() }
    val cidade = remember { FocusRequester() }
    val bairro = remember { FocusRequester() }
    val numero = remember { FocusRequester() }
    val complemento = remember { FocusRequester() }
    val cep = remember { FocusRequester() }
    val email = remember { FocusRequester() }
    val dataNascimento = remember { FocusRequester() }
    val rg = remember { FocusRequester() }
    val telefone = remember { FocusRequester() }
    val observacao = remember { FocusRequester() }
    val cadastrar = remember { FocusRequester() }

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
                value = clienteDto.value.nome,
                onValueChange = { clienteDto.value.nome = it },
                label = {
                    Text(
                        "Nome Cliente/RazãoSocial${if (clienteDto.value.nome.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequesterNome)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Tab) {
                            focusRequesterNomeFantasia.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColor,
                    focusedLabelColor = backgroundColor
                )
            )
            Row {

                OutlinedTextField(
                    value = clienteDto.value.fantasia,
                    onValueChange = { clienteDto.value.fantasia = it },
                    label = {
                        Text(
                            "Nome Fantasia",
                            style = TextStyle(fontSize = 12.sp)
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .height(60.dp)
                        .width(950.dp)
                        .focusRequester(focusRequesterNomeFantasia)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                rg.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = clienteDto.value.rg_ie,
                    onValueChange = { clienteDto.value.rg_ie = it },
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
                        .width(250.dp)
                        .focusRequester(rg)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                cpf.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }

            Row {
                OutlinedTextField(
                    value = cpfValue.value,
                    onValueChange = { newValue ->

                         newValue.text.replace("[^\\d]".toRegex(), "").let { cleaned ->
                             formatCpf(cleaned).let {
                                 cpfValue.value = TextFieldValue(
                                     AnnotatedString(it),
                                     TextRange(calculateCursorPosition(cleaned.length, it.length, it))
                                 )
                                 clienteDto.value.cnpj_cpf = it
                             }
                         }
                    },

                    label = {
                        Text(
                            "CPF${if (clienteDto.value.cnpj_cpf.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .height(60.dp)
                        .width(200.dp)
                        .focusRequester(cpf)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                cnpj.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    ),
                    trailingIcon = {
                        Image(
                            painter = rememberVectorPainter(image = Icons.Filled.Clear),
                            contentDescription = "Cpf clean",
                            modifier = Modifier.size(12.dp)
                                .clickable { cpfValue.value = TextFieldValue() }
                        )
                    }
                )

                OutlinedTextField(
                    value = cnpjValue.value,
                    onValueChange = { newValue ->
                         newValue.text.replace("[^\\d]".toRegex(), "").let { cleaned ->
                             formatCnpj(cleaned).let {
                                 cnpjValue.value = TextFieldValue (
                                     AnnotatedString(it),
                                     TextRange(calculateCursorPosition(cleaned.length, it.length, it))
                                 )
                                 clienteDto.value.cnpj_cpf = it
                             }
                         }
                    },

                    label = {
                        Text(
                            "CNPJ${if (clienteDto.value.cnpj_cpf.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(200.dp)
                        .focusRequester(cnpj)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                email.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    ),
                    trailingIcon = {
                        Image(
                            painter = rememberVectorPainter(image = Icons.Filled.Clear),
                            contentDescription = "Cpj clean",
                            modifier = Modifier
                                .size(12.dp)
                                .clickable { cnpjValue.value = TextFieldValue() }
                        )
                    }
                )

                OutlinedTextField(
                    value = clienteDto.value.email,
                    onValueChange = { clienteDto.value.email = it },
                    label = {
                        Text(
                            "E-mail",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(250.dp)
                        .focusRequester(email)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                telefone.requestFocus()
                                true
                            } else {
                                false
                            }
                        },


                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = clienteDto.value.telefone,
                    onValueChange = { clienteDto.value.telefone = it },
                    label = {
                        Text(
                            "Telefone${if (clienteDto.value.telefone.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(250.dp)
                        .focusRequester(telefone)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                dataNascimento.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = clienteDto.value.dataNascimento,
                    onValueChange = { clienteDto.value.dataNascimento = it },
                    label = {
                        Text(
                            "Data de Nascimento",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(200.dp)
                        .focusRequester(dataNascimento)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                logradouro.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }

            Row {
                OutlinedTextField(
                    value = enderecoDto.value.logradouro,
                    onValueChange = { enderecoDto.value.logradouro = it },
                    label = {
                        Text(
                            "Logradouro${if (enderecoDto.value.logradouro.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .height(60.dp)
                        .width(350.dp)
                        .focusRequester(logradouro)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                cidade.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
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
                            "Localidade${if (enderecoDto.value.localidade.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(350.dp)
                        .focusRequester(cidade)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                bairro.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )

                OutlinedTextField(
                    value = enderecoDto.value.bairro,
                    onValueChange = { enderecoDto.value.bairro = it },
                    label = {
                        Text(
                            "Bairro${if (enderecoDto.value.bairro.isEmpty() && errorMessage.isNotEmpty()) " *" else ""}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },

                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(350.dp)
                        .focusRequester(bairro)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                numero.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    )
                )
            }

            Row{
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
                    modifier = Modifier.width(100.dp)
                        .focusRequester(numero)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                complemento.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
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
                        .padding(start = 8.dp)
                        .focusRequester(complemento)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                cep.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
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
                        .padding(start = 8.dp)
                        .focusRequester(cep)
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Tab) {
                                observacao.requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor
                    ),
                    trailingIcon = {
                        Image(
                            painter = rememberVectorPainter(image = Icons.Filled.Search),
                            contentDescription = "Search",
                            modifier = Modifier
                                .clickable { fetchCep(enderecoDto.value.cep) }
                        )
                    }
                )
            }

            OutlinedTextField(
                value = clienteDto.value.observacao,
                onValueChange = { clienteDto.value.observacao = it },
                label = {
                    Text(
                        "Observação",
                        style = TextStyle(fontSize = 12.sp)
                    )
                },

                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .width(500.dp)
                    .focusRequester(observacao)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Tab) {
                            cadastrar.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
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
                        if (validarCampos(clienteDto.value)) {
                            bindCadastroCliente()
                        } else {
                            errorMessage = "Por favor, preencha todos os campos obrigatórios."
                        }
                    },
                    modifier = Modifier
                        .padding(start = 100.dp, top = 10.dp)
                        .height(35.dp)
                        .width(250.dp)
                        .focusRequester(cadastrar),
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

fun calculateCursorPosition(oldLength: Int, newLength: Int, formattedText: String): Int {
    var cursorPosition = newLength
    val formatCharactersCount = formattedText.count { it == '.' || it == '-' }

    if (newLength > oldLength) {
        cursorPosition += formatCharactersCount
    }

    else if (newLength < oldLength) {
        cursorPosition -= formatCharactersCount
    }

    return cursorPosition.coerceIn(0, formattedText.length)
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
        modifier = Modifier.padding(start = 18.dp),
        text = verificarRetornoStatus()
    )
}

@Composable
private fun iconControleCredito(onClick: () -> Unit) {
    Row(modifier = Modifier.padding(8.dp)) {
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
                .width(290.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier
                .background(cardBackgroundColor)
                .padding(16.dp)) {

                fecharControleCredito()
                Text(text = "Forma Autorizada", fontWeight = FontWeight.Bold)

                val formaAutorizada = remember { mutableStateOf(setOf<String>()) }
                clienteDto.value.formaAutorizada = formaAutorizada.value

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
                clienteDto.value.tipoVenda = tipoVenda.value

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
                    value = clienteDto.value.limiteCredito,
                    onValueChange = { clienteDto.value.limiteCredito = it },
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
            cliente.telefone.isNotEmpty()
}

@Composable
private fun limparPagina() {
    if (limparCampos.collectAsState().value) {
        clienteDto.value = ClienteDto()
        enderecoDto.value = EnderecoDto()
        limparCampos.value = false
        retornoStatus.value = 0
        cnpjValue.value = TextFieldValue()
        cpfValue.value = TextFieldValue()
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
                    value = "",
                    onValueChange = {  },
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


