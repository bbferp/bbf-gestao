package org.bff.erp.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bff.erp.model.Produtos
import org.bff.erp.networking.setProdutoApi

fun enviarProdutos(produtoNome: String) {
    CoroutineScope(Dispatchers.Main).launch {
        val produto = Produtos()
        produto.nomeProduto = produtoNome
        setProdutoApi(produto)
    }
}