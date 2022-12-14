package com.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发异常：ConcurrentModificationException
 */
public class MapTest {
    public static void main(String[] args) {
        //map是这样用的吗？ 不是，工作中不用HashMap
        //默认等价于什么？ new HashMap<>();
        //Map<String,String> map=new HashMap<>();

        //研究ConcurrentHashMap的原理
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
