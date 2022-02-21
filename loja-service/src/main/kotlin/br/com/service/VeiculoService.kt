package br.com.service

import br.com.dto.output.Veiculo
import br.com.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Singleton
class VeiculoService(
    private val veiculoHttp: VeiculoHttp,
    private val objectMaper: ObjectMapper
) {

    fun getVeiculo(id: Long): Veiculo{
        val veiculo = veiculoHttp.buscarVeiculoPorId(id)
        gravarCache(veiculo)
        return veiculo
    }

    fun gravarCache(veiculo: Veiculo){
        val jedisPool = JedisPool(JedisPoolConfig(),"127.0.0.1", 6379)
        val jedis = jedisPool.resource
        var veiculoJSON = objectMaper.writeValueAsString(veiculo)
        jedis.set(veiculo.id.toString(), veiculoJSON)

    }
}