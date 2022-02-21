package br.com.http

import br.com.dto.output.Veiculo
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker

@Client(id = "http://localhost:8080")
@CircuitBreaker
interface VeiculoHttp {

    @Get("/veiculos/{id}")
    fun buscarVeiculoPorId(@PathVariable id: Long): Veiculo
}