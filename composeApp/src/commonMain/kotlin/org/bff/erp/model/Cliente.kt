package org.bff.erp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import org.bff.erp.model.dto.ClienteDto

@Serializable
class Cliente {
    var id = ""
    var nome = ""
    var nomeFantasia = ""
    var razaoSocial = ""
    var cnpj_cpf = ""
    var endereco = ""
    var email = ""
    var dataNascimento = ""
    var rg_ie = ""
    var telefone = ""
    var observacao = ""
    var limiteCredito = ""
    var tipoVenda: Set<String> = emptySet()
    var formaAutorizada: Set<String> = emptySet()
    var vendedor = ""
}

fun clienteToClienteDTO(cliente:Cliente):ClienteDto {
   return ClienteDto().apply {
       id = cliente.id
       nome = cliente.nome
       nomeFantasia = cliente.nomeFantasia
       razaoSocial = cliente.razaoSocial
       cnpj_cpf = cliente.cnpj_cpf
       endereco = cliente.endereco
       email = cliente.email
       dataNascimento = cliente.dataNascimento
       rg_ie = cliente.rg_ie
       telefone = cliente.telefone
       observacao = cliente.observacao
       limiteCredito = cliente.limiteCredito
       tipoVenda = cliente.tipoVenda
       formaAutorizada = cliente.formaAutorizada
       vendedor = cliente.vendedor
   }
}