package com.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ConcurrentModificationException
 * set不安全解决办法
 * 1:Set<String> set= Collections.synchronizedSet(new HashSet<>());
 * 2:Set<String> set=new CopyOnWriteArraySet<>();
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set1=new HashSet<>();//HashSet的底层是HashMap
        //Set<String> set= Collections.synchronizedSet(new HashSet<>());

        Set<String> set=new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
