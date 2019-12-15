package com.jxedu;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/redis")
public class RedisController {
     private final StringRedisTemplate redisTemplate;
     @Resource(name = "redisTemplate")
     private ListOperations<String,Object> listOperations;
     @GetMapping("/message")
     public ResponseEntity<Void> getMessage(@RequestParam(value = "msg") String msg){
          redisTemplate.convertAndSend("redis-demo-topic",msg);
         return ResponseEntity.ok(null);
     }

     @GetMapping("/queue")
     public ResponseEntity<Void> getQueueMsg(@RequestParam(value = "msg") String msg){
         listOperations.leftPush("redisQueue",msg);
         return ResponseEntity.ok(null);
    }
}
