package com.jxedu.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.jxedu.entity.UserDemo;

/**
 * @author libin
 * @version 1.0
 * @description
 * 提供统一的，类似jsr-107风格的API访问Cache，并可通过注解创建并配置Cache实例
 * 通过注解实现声明式的方法缓存，支持TTL和两级缓存
 * 分布式缓存自动刷新，分布式锁 (2.2+)
 * 支持异步Cache API
 * Spring Boot支持
 * Key的生成策略和Value的序列化策略是可以定制的
 * 针对所有Cache实例和方法缓存的自动统计
 * @data 2019/12/16 10:03
 */

public interface JetCacheServiceDemo {

    @Cached(name="userCache-", key="#userId", expire = 3600,cacheType = CacheType.LOCAL)
    UserDemo getUserById(Integer userId);

    @CacheUpdate(name="userCache-", key="#user.id", value="#user")
    void updateUser(UserDemo user);

    @CacheInvalidate(name="userCache-", key="#userId")
    void deleteUser(Integer userId);

    UserDemo createCacheDemo(Integer id);

    void updateCacheDemo(Integer id, String name);

    void deleteCacheDemo(Integer id);

    UserDemo getCacheDemo(Integer id);
}
