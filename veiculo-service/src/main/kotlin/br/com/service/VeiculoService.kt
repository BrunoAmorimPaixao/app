package br.com.service

import br.com.repository.VeiculoRepository
import br.com.model.Veiculo
import jakarta.inject.Singleton

@Singleton
class VeiculoService(
    private val veiculoRepository: VeiculoRepository
) {
    fun salvarVeiculo(veiculo: Veiculo): Veiculo{
        return veiculoRepository.save(veiculo)
    }

    fun buscarVeiculoPorId(id: Long): Veiculo{
        return veiculoRepository.findById(id).get()
    }

}