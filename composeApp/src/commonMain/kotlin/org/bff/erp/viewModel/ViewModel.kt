package org.bff.erp.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario
import org.bff.erp.networking.setProdutoApi
import org.bff.erp.networking.setUsuarioLogado

var usuarioValidado = MutableStateFlow(false)

fun validarUsuario(nomeUsuario: String, senhaUsuario: String) {
    CoroutineScope(Dispatchers.Main).launch {
        val user = Usuario()
        user.nome = nomeUsuario
        user.senha = senhaUsuario

        setUsuarioLogado(user)

    }
}

fun enviarProdutos(produtoNome: String) {
    CoroutineScope(Dispatchers.Main).launch {
        val produto = Produtos()
        produto.nomeProduto = produtoNome
        setProdutoApi(produto)
    }
}