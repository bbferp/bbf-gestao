package org.bff.erp.networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario
import org.bff.erp.util.BaseApi.BASE
import org.bff.erp.viewModel.usuarioValidado
import org.w3c.xhr.XMLHttpRequest

actual suspend fun setProdutoApi(produtos: Produtos) {}

actual suspend fun setUsuarioLogado(usuario: Usuario) {
    try {
        CoroutineScope(Dispatchers.Main).launch {
            val prefix = usuario.nome
            val sufix = usuario.senha

            val json = Json.encodeToString(usuario)

            XMLHttpRequest().apply {
                open(
                    "PUT",
                    "https://$prefix-$sufix.$BASE/usuario"
                )

                setRequestHeader("Content-Type", "application/json")

                onload = {
                    if (status.toInt() == 200) {
                        usuarioValidado.value = true
                        println("Upload bem-sucedido: $responseText")

                    } else {
                        error("Erro na requisição: $status $statusText")
                    }
                }
                onerror = {
                    error("Erro na requisição: $status $statusText")
                }

                send(json)
            }
        }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}
