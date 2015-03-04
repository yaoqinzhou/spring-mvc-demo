package com.example.main;

import com.example.service.RedisService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestRedis {

    public  static void main(String[] args){

        try{
            ApplicationContext appContext = new FileSystemXmlApplicationContext("classpath:demo-service.xml");

            RedisService redisService = (RedisService)appContext.getBean("redisService");

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }
}
