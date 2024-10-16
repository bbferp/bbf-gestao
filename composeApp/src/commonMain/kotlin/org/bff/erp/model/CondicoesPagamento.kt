package org.bff.erp.model

import kotlinx.serialization.Serializable

@Serializable
 class CondicaoPagamento {
    var idCliente = ""
    var ativa = false
    var tipoCondPagamento = ""
    var formaCondPagamento = ""
 }