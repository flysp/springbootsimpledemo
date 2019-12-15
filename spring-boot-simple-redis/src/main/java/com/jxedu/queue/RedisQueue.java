package com.jxedu.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisQueue implements ApplicationRunner {
    private final RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private  ListOperations<String, Object> listOperations;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    /*    while (true) {
            Object msg = listOperations.rightPop("redisQueue",10, TimeUnit.SECONDS);
            log.info("出栈的消息：[{}]", msg);
        }*/
    }
}
