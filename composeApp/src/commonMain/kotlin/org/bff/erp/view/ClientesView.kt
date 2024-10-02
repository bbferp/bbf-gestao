import Cliente
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.bff.erp.util.DefaultColors.backgroundColor
import org.bff.erp.util.DefaultColors.cardBackgroundColor

@Composable
fun clienteScreen() {
    adicionarCliente()
}

@Composable
fun adicionarCliente() {
    val cliente = remember { Cliente() }

    LazyColumn(
        modifier = Modifier
            .padding(start = 80.dp)
            .background(backgroundColor)
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.background(color = cardBackgroundColor)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = cliente.nome,
                        onValueChange = { cliente.nome = it },
                        label = { Text("Nome Cliente") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.nomeFantasia,
                        onValueChange = { cliente.nomeFantasia = it },
                        label = { Text("Nome Fantasia") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.razaoSocial,
                        onValueChange = { cliente.razaoSocial = it },
                        label = { Text("Razão Social") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.cnpj,
                        onValueChange = { cliente.cnpj = it },
                        label = { Text("CNPJ") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.cpf,
                        onValueChange = { cliente.cpf = it },
                        label = { Text("CPF") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.email,
                        onValueChange = { cliente.email = it },
                        label = { Text("E-mail") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.telefone,
                        onValueChange = { cliente.telefone = it },
                        label = { Text("Telefone") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.endereco,
                        onValueChange = { cliente.endereco = it },
                        label = { Text("Endereço") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.dataNascimento,
                        onValueChange = { cliente.dataNascimento = it },
                        label = { Text("Data de Nascimento") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.rg,
                        onValueChange = { cliente.rg = it },
                        label = { Text("RG") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.ie,
                        onValueChange = { cliente.ie = it },
                        label = { Text("Inscrição Estadual (IE)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    OutlinedTextField(
                        value = cliente.observacao,
                        onValueChange = { cliente.observacao = it },
                        label = { Text("Observação") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = backgroundColor,
                            focusedLabelColor = backgroundColor
                        )
                    )

                    Button(
                        onClick = {
                            // Implementar lógica para enviar os dados
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
}
