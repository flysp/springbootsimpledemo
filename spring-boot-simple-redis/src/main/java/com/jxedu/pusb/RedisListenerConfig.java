package com.jxedu.pusb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {


    /**
     * 配置监听容器
     * @param redisConnectionFactory
     * @param messageListenerAdapter1
     * @param messageListenerAdapter2
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       @Qualifier(value = "messageListenerAdapter1") MessageListenerAdapter messageListenerAdapter1,
                                                                       @Qualifier(value = "messageListenerAdapter2") MessageListenerAdapter messageListenerAdapter2){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //可以添加多个
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter1,new PatternTopic("redis-demo-topic"));
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter2,new ChannelTopic("redis-demo-topic"));
        redisMessageListenerContainer.afterPropertiesSet();
        return redisMessageListenerContainer;
    }

    /**
     * 监听器1
     * @param redisPusb1
     * @return
     */
    @Bean("messageListenerAdapter1")
    public MessageListenerAdapter messageListenerAdapter1(@Qualifier(value = "redispusb1") RedisPusb redisPusb1){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisPusb1,"redisConsummerMessage1");
        return messageListenerAdapter;
    }

    /**
     * 监听器2
     * @param redisPusb2
     * @return
     */
    @Bean("messageListenerAdapter2")
    public MessageListenerAdapter messageListenerAdapter2(@Qualifier(value = "redispusb2") RedisPusb2 redisPusb2){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisPusb2,"redisConsummerMessage2");
        return messageListenerAdapter;
    }
}
