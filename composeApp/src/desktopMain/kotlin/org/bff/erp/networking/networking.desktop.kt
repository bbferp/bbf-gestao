package org.bff.erp.networking

import org.bff.erp.model.Cliente
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario

actual suspend fun setProdutoApi(produtos: Produtos) {}

actual suspend fun setUsuarioLogado(usuario: Usuario) {}

actual suspend fun setCadastroCliente(cliente: Cliente) {
}
