package com.match.model;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisDemo {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");



       /* //查看服务是否运行
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
        //设置redis list数据
        jedis.lpush("reMap","tolyzty","123");
        jedis.lpush("reMap","520000");
        jedis.lpush("reMap","13500000000");
        List<String> list = jedis.lrange("reMap", 0 ,3);
        System.out.println("redis 存储的List："+list);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }*/


    }
}
