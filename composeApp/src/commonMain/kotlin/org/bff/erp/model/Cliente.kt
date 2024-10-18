package org.bff.erp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import org.bff.erp.viewModel.enderecoDto

@Serializable
class Cliente {
    var id = ""
    var nome = ""
    var fantasia = ""
    var cnpj_cpf = ""
    var endereco = Endereco()
    var email = ""
    var dataNascimento = ""
    var rg_ie = ""
    var telefone = ""
    var observacao = ""
    var limiteCredito = ""
    var tipoVenda: Set<String> = emptySet()
    var formaAutorizada: Set<String> = emptySet()
    var vendedor = ""
    var clienteTemImagem = false
}

class ClienteDto {
    var id by mutableStateOf("")
    var nome by mutableStateOf("")
    var fantasia by mutableStateOf("")
    var cnpj_cpf by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var rg_ie by mutableStateOf("")
    var telefone by mutableStateOf("")
    var observacao by mutableStateOf("")
    var limiteCredito by mutableStateOf("")
    var tipoVenda: Set<String> = emptySet()
    var formaAutorizada: Set<String> = emptySet()
    var vendedor by mutableStateOf("")
    var temImagem by mutableStateOf(false)
}

fun clienteToClienteDTO(cliente:Cliente):ClienteDto {
   return ClienteDto().apply {
       id = cliente.id
       nome = cliente.nome
       fantasia = cliente.fantasia
       cnpj_cpf = cliente.cnpj_cpf
       email = cliente.email
       dataNascimento = cliente.dataNascimento
       rg_ie = cliente.rg_ie
       telefone = cliente.telefone
       observacao = cliente.observacao
       limiteCredito = cliente.limiteCredito
       tipoVenda = cliente.tipoVenda
       formaAutorizada = cliente.formaAutorizada
       vendedor = cliente.vendedor
       temImagem = cliente.clienteTemImagem

       enderecoDto.value.localidade = cliente.endereco.localidade
       enderecoDto.value.cep = cliente.endereco.cep
       enderecoDto.value.logradouro = cliente.endereco.logradouro
       enderecoDto.value.numero = cliente.endereco.numero
       enderecoDto.value.estado = cliente.endereco.estado
       enderecoDto.value.complemento = cliente.endereco.complemento
       enderecoDto.value.bairro = cliente.endereco.bairro

   }
}