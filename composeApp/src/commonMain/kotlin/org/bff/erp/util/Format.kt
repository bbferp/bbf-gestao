package org.bff.erp.util

object Format {

    fun formatCnpj(cnpj: String): String {
        val cleaned = cnpj.replace("[^\\d]".toRegex(), "")

        return when {
            cleaned.length <= 2 -> cleaned
            cleaned.length <= 5 -> "${cleaned.take(2)}.${cleaned.drop(2)}"
            cleaned.length <= 8 -> "${cleaned.take(2)}.${cleaned.substring(2, 5)}.${cleaned.drop(5)}"
            cleaned.length <= 12 -> "${cleaned.take(2)}.${cleaned.substring(2, 5)}.${cleaned.substring(5, 8)}/${cleaned.drop(8)}"
            cleaned.length == 14 -> "${cleaned.take(2)}.${cleaned.substring(2, 5)}.${cleaned.substring(5, 8)}/${cleaned.substring(8, 12)}-${cleaned.takeLast(2)}"
            else -> cleaned
        }
    }

    fun formatCpf(cpf: String): String {
        val cleaned = cpf.replace("[^\\d]".toRegex(), "")
        return when {
            cleaned.length < 4 -> cleaned
            cleaned.length < 7 -> "${cleaned.take(3)}.${cleaned.drop(3)}"
            cleaned.length < 10 -> "${cleaned.take(3)}.${cleaned.substring(3, 6)}.${cleaned.drop(6)}"
            else -> "${cleaned.take(3)}.${cleaned.substring(3, 6)}.${cleaned.substring(6, 9)}-${cleaned.takeLast(2)}"
        }
    }

    fun formatTelefone(telefone: String): String {
        val cleaned = telefone.replace("[^\\d]".toRegex(), "")

        return when {
            cleaned.length < 3 -> cleaned
            cleaned.length < 7 -> "(${cleaned.take(2)}) ${cleaned.drop(2)}"
            cleaned.length < 11 -> "(${cleaned.take(2)}) ${cleaned[2]} ${cleaned.substring(3)}"
            cleaned.length == 11 -> "(${cleaned.take(2)}) ${cleaned[2]} ${cleaned.substring(3, 7)}-${cleaned.takeLast(4)}"
            else -> cleaned
        }
    }

    fun formatDataNascimento(data: String): String {
        val cleaned = data.replace("[^\\d]".toRegex(), "")

        return when {
            cleaned.length < 2 -> cleaned
            cleaned.length < 4 -> "${cleaned.take(2)}/${cleaned.drop(2)}"
            cleaned.length < 8 -> "${cleaned.take(2)}/${cleaned.substring(2, 4)}/${cleaned.drop(4)}"
            cleaned.length == 8 -> "${cleaned.take(2)}/${cleaned.substring(2, 4)}/${cleaned.substring(4, 8)}"
            else -> cleaned
        }
    }
}