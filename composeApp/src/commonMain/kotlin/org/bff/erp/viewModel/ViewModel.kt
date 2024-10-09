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
        val cliente = Cliente().apply {
            nome = clienteDto.nome
            nomeFantasia = clienteDto.nomeFantasia
            razaoSocial = clienteDto.razaoSocial
            cnpj = clienteDto.cnpj
            cpf = clienteDto.cpf
            endereco = clienteDto.endereco
            email = clienteDto.email
            dataNascimento = clienteDto.dataNascimento
            rg = clienteDto.rg
            ie = clienteDto.ie
            telefone = clienteDto.telefone
            observacao = clienteDto.observacao
            limiteCredito = clienteDto.limiteCredito
            tipoVenda = clienteDto.tipoVenda
            formaAutorizada = clienteDto.formaAutorizada
            vendedor = clienteDto.vendedor
        }

        setCadastroCliente(cliente)
    }
}