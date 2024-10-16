package org.bff.erp.networking

import imagemSelecionada
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bff.erp.model.Cliente
import org.bff.erp.model.Endereco
import org.bff.erp.model.Produtos
import org.bff.erp.model.Usuario
import org.bff.erp.util.BaseApi.BASE
import org.bff.erp.viewModel.retornoStatus
import org.bff.erp.viewModel.usuarioLogado
import org.bff.erp.viewModel.usuarioValidado
import org.w3c.files.File
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

            val prefix = usuarioLogado.value.nome
            val sufix = usuarioLogado.value.senha


            imagemSelecionada.value?.let {
                setImageCliente(it,cliente)
                cliente.clienteTemImagem = true
            }

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

actual suspend fun setUpdateCliente(clientesList: MutableList<Cliente>) {
    try {
            val prefix = usuarioLogado.value.nome
             val sufix = usuarioLogado.value.senha

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
                println("send requisição $clientesList")

                send(Json.encodeToString(clientesList))
            }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}

fun setImageCliente(file: File, cliente: Cliente) {
    try {
         val prefix = usuarioLogado.value.nome
         val sufix = usuarioLogado.value.senha

        XMLHttpRequest().apply {
            open(
                "PUT",
                "https://$prefix-$sufix.$BASE/imagens/${cliente.id}"
            )

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

            send(file)
            imagemSelecionada.value = null
        }
    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}

actual suspend fun getCep(cep: String): Endereco {
    var endereco = Endereco()

    try {
        val response = window.fetch("https://viacep.com.br/ws/$cep/json/").then { res ->
            if (res.ok) {
                res.text()
            } else {
                throw Exception("Erro na requisição: ${res.status} ${res.statusText}")
            }
        }

        response.await<JsString>().toString().let { retorno ->
            val json = Json { ignoreUnknownKeys = true }
            endereco = json.decodeFromString(retorno)
            println("Dados do CEP: $endereco")

        }
    } catch (e: Exception) {
        println("Erro ao buscar o CEP: ${e.message}")
    }

    return endereco
}
