import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bff.erp.itemCadastrarSubMenu
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor
import org.bff.erp.viewModel.validarCadastroCliente

@Composable
fun clienteScreen() {
    adicionarCliente()
}

@Composable
fun adicionarCliente() {
    val cliente = remember { Cliente() }

    LazyColumn(
        modifier = Modifier
            .padding(start = 80.dp, top = 100.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(12.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.background(color = cardBackgroundColor)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = cliente.nome,
                        onValueChange = { cliente.nome = it },
                        label = {
                            Text(
                                "Nome Cliente",
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
                        value = cliente.nomeFantasia,
                        onValueChange = { cliente.nomeFantasia = it },
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
                        value = cliente.razaoSocial,
                        onValueChange = { cliente.razaoSocial = it },
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
                            value = cliente.cnpj,
                            onValueChange = { cliente.cnpj = it },
                            label = {
                                Text(
                                    "CNPJ",
                                    style = TextStyle(fontSize = 12.sp)
                                )
                            },

                            textStyle = TextStyle(fontSize = 12.sp),
                            modifier = Modifier
                                .height(60.dp)
                                .width(170.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = backgroundColor,
                                focusedLabelColor = backgroundColor
                            )
                        )

                        OutlinedTextField(
                            value = cliente.cpf,
                            onValueChange = { cliente.cpf = it },
                            label = {
                                Text(
                                    "CPF",
                                    style = TextStyle(fontSize = 12.sp)
                                )
                            },
                            textStyle = TextStyle(fontSize = 12.sp),
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(60.dp)
                                .width(170.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = backgroundColor,
                                focusedLabelColor = backgroundColor
                            )
                        )

                        OutlinedTextField(
                            value = cliente.email,
                            onValueChange = { cliente.email = it },
                            label = {
                                Text(
                                    "E-mail",
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

                        OutlinedTextField(
                            value = cliente.telefone,
                            onValueChange = { cliente.telefone = it },
                            label = {
                                Text(
                                    "Telefone",
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

                    Row {
                        OutlinedTextField(
                            value = cliente.dataNascimento,
                            onValueChange = { cliente.dataNascimento = it },
                            label = {
                                Text(
                                    "Data de Nascimento",
                                    style = TextStyle(fontSize = 12.sp)
                                )
                            },
                            textStyle = TextStyle(fontSize = 12.sp),
                            modifier = Modifier
                                .height(60.dp)
                                .width(250.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = backgroundColor,
                                focusedLabelColor = backgroundColor
                            )
                        )

                        OutlinedTextField(
                            value = cliente.rg,
                            onValueChange = { cliente.rg = it },
                            label = {
                                Text(
                                    "RG",
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

                        OutlinedTextField(
                            value = cliente.ie,
                            onValueChange = { cliente.ie = it },
                            label = {
                                Text(
                                    "Inscrição Estadual (IE)",
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
                        value = cliente.endereco,
                        onValueChange = { cliente.endereco = it },
                        label = {
                            Text(
                                "Endereço",
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
                        value = cliente.observacao,
                        onValueChange = { cliente.observacao = it },
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

                    Button(
                        onClick = {
                            validarCadastroCliente(cliente)
                        },
                        modifier = Modifier
                            .padding(start = 350.dp)
                            .height(35.dp)
                            .width(250.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                    ) {
                        Text(text = "Cadastrar", color = Color.White)
                    }
                }
            }
        }
    }
}
