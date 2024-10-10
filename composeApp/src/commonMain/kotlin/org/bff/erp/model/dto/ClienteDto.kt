package org.bff.erp.model.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class ClienteDto {
    var id by mutableStateOf("")
    var nome by mutableStateOf("")
    var nomeFantasia by mutableStateOf("")
    var razaoSocial by mutableStateOf("")
    var cnpj_cpf by mutableStateOf("")
    var endereco by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var rg_ie by mutableStateOf("")
    var telefone by mutableStateOf("")
    var observacao by mutableStateOf("")
    var limiteCredito by mutableStateOf("")
    var tipoVenda: Set<String> = emptySet()
    var formaAutorizada: Set<String> = emptySet()
    var vendedor by mutableStateOf("")
}