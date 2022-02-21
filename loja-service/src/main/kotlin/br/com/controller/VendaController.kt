package br.com.controller

import br.com.dto.input.vendaInput
import br.com.service.vendaService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/vendas")
class VendaController(
    private val vendaService: vendaService
) {

    @Post
    fun realizarVenda(@Body vendaInput: vendaInput){
        vendaService.realizarVenda(vendaInput)

    }
}