package org.bff.erp.networking

import org.bff.erp.model.Cliente
import org.bff.erp.model.Endereco
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario

actual suspend fun setProdutoApi(produtos: Produtos) {
}

actual suspend fun setUsuarioLogado(usuario: Usuario) {
}

actual suspend fun setCadastroCliente(cliente: Cliente) {
}

actual suspend fun setUpdateCliente(clientesList: MutableList<Cliente>) {
}


actual suspend fun getCep(cep: String): Endereco {
    return Endereco()
}

actual suspend fun getCnpj(cnpj: String): RetornoCnpj? {
    return null
}