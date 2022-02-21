package br.com.dto.output

import java.math.BigDecimal

data class Parcela(
    val valor: BigDecimal,
    val dataVencimento: String
)
