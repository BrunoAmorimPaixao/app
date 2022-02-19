package br.com.controller

import br.com.model.Veiculo
import br.com.service.VeiculoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.persistence.Id

@Controller("/veiculos")
class VeiculoController(
    private val veiculoService: VeiculoService
) {

    @Post
    fun salvarVeiculo(@Body veiculo: Veiculo): HttpResponse<Veiculo>{
        return HttpResponse.created(veiculoService.salvarVeiculo(veiculo))
    }

    @Get("/{id}")
    fun buscarVeiculoPorId(@PathVariable id: Long): Veiculo{
        return veiculoService.buscarVeiculoPorId(id)
    }
}