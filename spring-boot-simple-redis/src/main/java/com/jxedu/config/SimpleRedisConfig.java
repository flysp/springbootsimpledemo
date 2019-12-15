package com.jxedu.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

/**
 * @author libin
 * @date 2019-12-14
 */
@Configuration
public class SimpleRedisConfig {

     @Bean("redisTemplate")
     public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
         //序列化
         StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(Charset.forName("UTF-8"));

       //  GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = getGenericJackson2JsonRedisSerializer();
//         Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//         FastJsonRedisSerializer<Object> objectFastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
         GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = getGenericFastJsonRedisSerializer();
         redisTemplate.setKeySerializer(stringRedisSerializer);
         //redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//         redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);
//         redisTemplate.setValueSerializer(objectFastJsonRedisSerializer);
         redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);
         redisTemplate.setHashKeySerializer(stringRedisSerializer);
//         redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//         redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);
//         redisTemplate.setHashValueSerializer(objectFastJsonRedisSerializer);
         redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
         //连接
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         return redisTemplate;
     }

     private GenericFastJsonRedisSerializer getGenericFastJsonRedisSerializer(){
         GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
         return genericFastJsonRedisSerializer;
     }

    private GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return new GenericJackson2JsonRedisSerializer(mapper);
    }

    @Bean("stringRedisTemplate")
     public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
         StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
         stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
         return stringRedisTemplate;
     }
}
