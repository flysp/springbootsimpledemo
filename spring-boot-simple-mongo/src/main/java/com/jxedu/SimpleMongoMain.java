package com.jxedu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SimpleMongoMain implements ApplicationRunner {

     private final MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SimpleMongoMain.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

       log.info("mongo测试容器启动【{}】",mongoTemplate.getDb());
    }
}
