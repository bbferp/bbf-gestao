package org.bff.erp.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bff.erp.model.Cliente
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario
import org.bff.erp.util.BaseApi.BASE
import org.bff.erp.viewModel.retornoStatus
import org.bff.erp.viewModel.usuarioValidado
import org.w3c.xhr.XMLHttpRequest

actual suspend fun setProdutoApi(produtos: Produtos) {}

actual suspend fun setUsuarioLogado(usuario: Usuario) {
    try {
        CoroutineScope(Dispatchers.Main).launch {
            val prefix = usuario.nome
            val sufix = usuario.senha


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

                send(Json.encodeToString(usuario))
            }
        }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}

actual suspend fun setCadastroCliente(cliente: Cliente) {
    try {
        CoroutineScope(Dispatchers.Main).launch {
            var allClientesList = mutableListOf<Cliente>()

           // val prefix = usuarioLogado.value.nome
           // val sufix = usuarioLogado.value.senha
            val prefix = "aromas"
            val sufix = "01"

            allClientesList = getAllClientes(prefix,sufix)
            allClientesList.add(cliente)


            XMLHttpRequest().apply {
                open(
                    "PUT",
                    "https://$prefix-$sufix.$BASE/clientes"
                )

                setRequestHeader("Content-Type", "application/json")

                onload = {
                    if (status.toInt() == 200) {
                        retornoStatus.value = 200
                        println("Upload bem-sucedido: $responseText")

                    } else {
                        retornoStatus.value = 400
                        error("Erro na requisição: $status $statusText")
                    }
                }
                onerror = {
                    retornoStatus.value = 400
                    error("Erro na requisição: $status $statusText")
                }
                 println("send requisição $cliente")

                send(Json.encodeToString(allClientesList))
            }
        }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}

suspend fun getAllClientes(prefix: String, sufix: String): MutableList<Cliente> {
    val allClientesList = mutableListOf<Cliente>()

    try {
        val response = window.fetch("https://$prefix-$sufix.$BASE/clientes").then {
            res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val clientes: List<Cliente> = Json.decodeFromString(retorno)
            println("Data Clientes LIST:$clientes")
            allClientesList.addAll(clientes)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allClientesList
}
