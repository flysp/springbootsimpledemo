package com.jxedu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SimpleRedisMain.class})
public class RedisStructureTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    @Resource(name = "redisTemplate")
    public ValueOperations<String,Object> valueOperations;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    public void testBitMap(){
//        valueOperations.setBit("bit1",1,true);
        valueOperations.setBit("bit1",2,true);
//        valueOperations.setBit("bit2",2,false);
    }

    @Test
    public void testBit2Map(){
        Boolean bit1 = valueOperations.getBit("bit1", 1);
        Boolean bit2 = valueOperations.getBit("bit1", 2);
        System.out.println("+++++++++++"+bit1);
        System.out.println("+++++++++++"+bit2);
        Long bitSize = redisConnectionFactory.getConnection().bitCount("bit1".getBytes());
        System.out.println("+++++++++++Size:"+bitSize);
    }

}
