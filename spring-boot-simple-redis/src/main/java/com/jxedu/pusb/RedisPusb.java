package com.jxedu.pusb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("redispusb1")
public class RedisPusb {

     public String redisConsummerMessage1(String message){
          log.info("1接收到的消息【{}】",message);
          return message;
     }
}
