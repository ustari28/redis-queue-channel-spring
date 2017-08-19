package com.alan.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.annotation.Transformer;
import redis.clients.jedis.JedisPoolConfig;

import java.io.UnsupportedEncodingException;

/**
 * @author Alan Dávila<br>
 * 08 ene. 2017 22:54
 */
@Configuration
@Slf4j
public class ConfigurationApp {

    @Value("${redis.hostname}")
    private String hostName;
    @Value(("${redis.port}"))
    private Integer port;
    @Value("${redis.connection.max}")
    private Integer maxTotal;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(Boolean.TRUE);
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(final JedisPoolConfig jedisPoolConfig) {
        final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

    @Transformer(inputChannel = "input-channel-redis", outputChannel = "output-channel-redis")
    public String transformBean(final byte[] input) throws UnsupportedEncodingException {

        try {
            log.info("input " + new String(input, Constants.ENCODING));
        } catch (UnsupportedEncodingException e) {
            log.error("encoding doesnt supported");
        }
        return new String(input, Constants.ENCODING);
    }

}
