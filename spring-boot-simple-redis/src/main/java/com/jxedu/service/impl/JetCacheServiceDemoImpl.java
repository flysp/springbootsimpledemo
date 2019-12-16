package com.jxedu.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.jxedu.entity.UserDemo;
import com.jxedu.service.JetCacheServiceDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author libin
 * @version 1.0
 * @description
 * @data 2019/12/16 10:07
 */
@Slf4j
@Service
public class JetCacheServiceDemoImpl implements JetCacheServiceDemo {

    /**
     * 自动刷新和加载保护是JetCache的大杀器，对于加载开销比较大的对象，为了防止缓存未命中时的高并发访问打爆数据库
     */
    @CreateCache(keyConvertor = "fastjson",name = "jetCacheDemo",cacheType = CacheType.LOCAL,expire = 3600,
            timeUnit = TimeUnit.SECONDS,localLimit = 101,serialPolicy = "java",localExpire = 3600)
    @CacheRefresh(refresh = 1, stopRefreshAfterLastAccess = 10, timeUnit = TimeUnit.SECONDS)
    @CachePenetrationProtect
    private Cache<Integer,UserDemo> jetCache;

    @Override
    public UserDemo getUserById(Integer userId) {
        log.info("获取并创建用户id{}",userId);
        return new UserDemo(userId,"userdemo"+userId);
    }

    @Override
    public void updateUser(UserDemo user) {
        log.info("更新用户id{}", user.getId());
    }

    @Override
    public void deleteUser(Integer userId) {
        log.info("删除用户{}", userId);
    }

    @Override
    public UserDemo createCacheDemo(Integer id) {
        UserDemo userDemo = new UserDemo(id,"ctcdemo1");
         log.info("创建本地cache demo 【{}】",userDemo);
        jetCache.putIfAbsent(id,userDemo);
        return userDemo;
    }

    @Override
    public void updateCacheDemo(Integer id, String name) {
        UserDemo ctcdemo2 = new UserDemo(id, name);
        log.info("更新本地cache demo 【{}】",ctcdemo2);
        jetCache.put(id,ctcdemo2);
    }

    @Override
    public void deleteCacheDemo(Integer id) {
        log.info("删除本地cache demo【{}】",id);
        jetCache.remove(id);
    }

    @Override
    public UserDemo getCacheDemo(Integer id) {
        UserDemo userdemo = jetCache.get(id);
        return userdemo;
    }

}
