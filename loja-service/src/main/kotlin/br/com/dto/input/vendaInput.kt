package br.com.dto.input

import java.math.BigDecimal

data class vendaInput(
    val cliente: String,
    val veiculo: Long,
    val valor: BigDecimal,
    val quantidadeParcelas: Int
)
