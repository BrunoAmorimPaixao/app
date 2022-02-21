package br.com.http.FallBack

import br.com.dto.output.Veiculo
import br.com.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoHttpFallBack(
    private val objectMaper: ObjectMapper
): VeiculoHttp {
    override fun buscarVeiculoPorId(id: Long): Veiculo {
        val jedisPool = JedisPool(JedisPoolConfig(),"127.0.0.1", 6379)
        val jedis = jedisPool.resource
        //recuperar o valor que esta no cache
        val veiculoJSON = jedis.get(id.toString())
        val veiculo = objectMaper.readValue(veiculoJSON, Veiculo::class.java)
        return  veiculo


    }
}