package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void getMailInfo(){
        System.out.println("执行到getMailInfo方法中");

        String mailInfoStr = stringRedisTemplate.opsForList().rightPop("testEmails");

        System.out.println("mailInfoStr = " + mailInfoStr);
    }

    /**
     * 向set中插入数据
     */
    public void addSet(){
        System.out.println("执行到addSet中");

        stringRedisTemplate.opsForSet().add("test","test set info");
    }

}
