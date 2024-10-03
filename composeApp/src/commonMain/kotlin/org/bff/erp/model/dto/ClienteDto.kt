package org.bff.erp.model.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class ClienteDto {
    var nome by mutableStateOf("")
    var nomeFantasia by mutableStateOf("")
    var razaoSocial by mutableStateOf("")
    var cnpj by mutableStateOf("")
    var cpf by mutableStateOf("")
    var endereco by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var rg by mutableStateOf("")
    var ie by mutableStateOf("")
    var telefone by mutableStateOf("")
    var observacao by mutableStateOf("")
}