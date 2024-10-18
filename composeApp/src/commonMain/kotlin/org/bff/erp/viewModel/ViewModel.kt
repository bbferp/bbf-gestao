package org.bff.erp.viewModel

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.bff.erp.model.*
import org.bff.erp.networking.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

var usuarioValidado = MutableStateFlow(false)
var usuarioLogado  = MutableStateFlow(Usuario())
var retornoStatus = MutableStateFlow(0)
var enderecoDto = mutableStateOf(EnderecoDto())
var clienteDto = mutableStateOf(ClienteDto())

fun validarUsuario(nomeUsuario: String, senhaUsuario: String) {
    CoroutineScope(Dispatchers.Main).launch {
        usuarioLogado.value.nome = nomeUsuario
        usuarioLogado.value.senha = senhaUsuario

        setUsuarioLogado(usuarioLogado.value)
    }
}

fun enviarProdutos(produtoNome: String) {
    CoroutineScope(Dispatchers.Main).launch {
        val produto = Produtos()
        produto.nomeProduto = produtoNome
        setProdutoApi(produto)
    }
}

fun bindCadastroCliente() {
    CoroutineScope(Dispatchers.Main).launch {
        setCadastroCliente(convertDtoToCadastroCliente())
    }
}

@OptIn(ExperimentalUuidApi::class)
fun convertDtoToCadastroCliente(): Cliente {
     return Cliente().apply {
        id = Uuid.random().toString()
        nome = clienteDto.value.nome.trim()
        fantasia = clienteDto.value.fantasia.trim()
        cnpj_cpf = clienteDto.value.cnpj_cpf.trim()
        endereco = getEnderecoCadastrado(id)
        email = clienteDto.value.email.trim()
        dataNascimento = clienteDto.value.dataNascimento.trim()
        rg_ie = clienteDto.value.rg_ie.trim()
        telefone = clienteDto.value.telefone.trim()
        observacao = clienteDto.value.observacao.trim()
        limiteCredito = clienteDto.value.limiteCredito.trim()
        tipoVenda = clienteDto.value.tipoVenda
        formaAutorizada = clienteDto.value.formaAutorizada
        vendedor = clienteDto.value.vendedor.trim()
    }
}

fun getEnderecoCadastrado(id: String): Endereco {
    return Endereco().apply {
     idCliente = id
     logradouro = enderecoDto.value.logradouro.trim()
     localidade = enderecoDto.value.localidade.trim()
     bairro = enderecoDto.value.bairro.trim()
     numero = enderecoDto.value.numero.trim()
     estado = enderecoDto.value.estado.trim()
     complemento = enderecoDto.value.complemento.trim()
     cep = enderecoDto.value.cep.trim()
    }
}

fun setupCondicaoPagamento() {
  var condiSelecionada = CondicaoPagamento.apply {

  }
}

fun fetchCep(cep: String) {
    CoroutineScope(Dispatchers.Main).launch {
       enderecoDto.value = enderecoToEnderecoDto(getCep(cep))
    }
}

