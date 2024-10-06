package org.bff.erp.model

import kotlinx.serialization.Serializable

@Serializable
class Cliente {
    var id = ""
    var nome = ""
    var nomeFantasia = ""
    var razaoSocial = ""
    var cnpj = ""
    var cpf = ""
    var endereco = ""
    var email = ""
    var dataNascimento = ""
    var rg = ""
    var ie = ""
    var telefone = ""
    var observacao = ""
    var tipoVenda: Set<String> = emptySet()
    var formaAutorizada: Set<String> = emptySet()
    var vendedor = ""
}