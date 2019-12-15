package com.jxedu.pusb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("redispusb2")
public class RedisPusb2 {

     public String redisConsummerMessage2(String message){
          log.info("2接收到的消息【{}】",message);
          return message;
     }
}
