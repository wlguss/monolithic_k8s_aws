package com.example.monolithic.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // 로컬이 아닌 클라우드 환경에서 DB 사용을 위해서는 해당 정보 등록 필요 !! (yaml파일에 등록한 정보 가져오기)
    @Value("${spring.data.redis.host}")
    private String host ;
    @Value("${spring.data.redis.port}")
    private int port ;

    // 클라우드 환경 정보가 들어간 팩토리 반환하는 객체 등록 
    @Bean
    @Qualifier("tokenRedis") 
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration() ;
        configuration.setHostName(host);
        configuration.setPort(port);

        return new LettuceConnectionFactory(configuration) ;
    }

    @Bean
    @Qualifier("tokenRedis")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory((rcf));

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }

}
