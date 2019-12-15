package com.jxedu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author libin
 */
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleRedisMain implements ApplicationRunner {

     private final RedisTemplate<String,Object> redisTemplate;
     @Resource(name = "redisTemplate")
     private ValueOperations<String,Object> valueOperationsRd;

     private final StringRedisTemplate stringRedisTemplate;
     @Resource(name = "stringRedisTemplate")
     private ValueOperations<String,String> valueOperationsStr;

    public static void main(String[] args) {
        SpringApplication.run(SimpleRedisMain.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("容器启动：[{}]",redisTemplate.getClientList());
//        valueOperationsStr.set("srdt","srdt",10, TimeUnit.MINUTES);
//        valueOperationsRd.set("srdtRd","srdtRd",10,TimeUnit.MINUTES);
//        valueOperationsRd.set("srdtRdcc","srdtRd",10,TimeUnit.MINUTES);
//        valueOperationsRd.set("srdtRdfast","srdtRd",10,TimeUnit.MINUTES);
//        valueOperationsRd.set("srdtRdfastGeric","srdtRd",10,TimeUnit.MINUTES);
         stringRedisTemplate.convertAndSend("redis-demo-topic","测试收到了没？");
    }
}
