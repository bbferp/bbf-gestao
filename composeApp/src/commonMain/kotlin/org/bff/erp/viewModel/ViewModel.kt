package org.bff.erp.viewModel

import org.bff.erp.model.dto.ClienteDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.bff.erp.model.Cliente
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario
import org.bff.erp.networking.setCadastroCliente
import org.bff.erp.networking.setProdutoApi
import org.bff.erp.networking.setUsuarioLogado
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

var usuarioValidado = MutableStateFlow(false)
var usuarioLogado  = MutableStateFlow(Usuario())
var retornoStatus = MutableStateFlow(0)

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

fun bindCadastroCliente(clienteDto: ClienteDto) {
    CoroutineScope(Dispatchers.Main).launch {
        setCadastroCliente(convertDtoToCadastroCliente(clienteDto))
    }
}

@OptIn(ExperimentalUuidApi::class)
fun convertDtoToCadastroCliente(clienteDto: ClienteDto):Cliente {
    val cliente = Cliente().apply {
        id = Uuid.random().toString()
        nome = clienteDto.nome
        nomeFantasia = clienteDto.nomeFantasia
        razaoSocial = clienteDto.razaoSocial
        cnpj_cpf = clienteDto.cnpj_cpf
        endereco = clienteDto.endereco
        email = clienteDto.email
        dataNascimento = clienteDto.dataNascimento
        rg_ie = clienteDto.rg_ie
        telefone = clienteDto.telefone
        observacao = clienteDto.observacao
        limiteCredito = clienteDto.limiteCredito
        tipoVenda = clienteDto.tipoVenda
        formaAutorizada = clienteDto.formaAutorizada
        vendedor = clienteDto.vendedor
    }
    return cliente
}

