package org.bff.erp.networking

import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario

expect suspend fun setUsuarioLogado(usuario: Usuario)

expect suspend fun setProdutoApi(produtos: Produtos)