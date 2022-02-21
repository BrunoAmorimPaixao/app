package br.com.service

import br.com.dto.input.vendaInput
import br.com.dto.output.Parcela
import br.com.dto.output.Venda
import br.com.http.VeiculoHttp
import br.com.producer.VendaProducer
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import java.time.LocalDate
import java.util.UUID

@Singleton
class vendaService(
    private val veiculoService: VeiculoService,
    private val vendaProducer: VendaProducer,
    private val objectMapper: ObjectMapper
) {

    fun realizarVenda(vendaInput: vendaInput): Venda {
        val veiculo = veiculoService.getVeiculo(vendaInput.veiculo)
        var parcelas: List<Parcela> = ArrayList<Parcela>()
        var valorParcela = vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal())
        var dataVencimento = LocalDate.now().plusMonths(1)

        for (i in 1..vendaInput.quantidadeParcelas) {
            var parcela = Parcela(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1)
        }

        var venda = Venda(vendaInput.cliente, veiculo, vendaInput.valor, parcelas)
        println(venda)
        confirmarVenda(venda)
        return venda
    }

    fun confirmarVenda(venda: Venda){
        var vendaJSON = objectMapper.writeValueAsString(venda)
        vendaProducer.publicarVenda(UUID.randomUUID().toString() ,vendaJSON.toString())
    }


}