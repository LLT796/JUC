package com.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException:并发修改异常
/**
 * 并发下ArrayList是不安全的
 * 解决方案：
 * 1:List<String> list=new Vector<>();
 * 2:List<String> list= Collections.synchronizedList(new ArrayList<>());
 * 3:List<String> list= new CopyOnWriteArrayList<>();
 */
    //CopyOnWrite 写入时复制 计算机程序设计领域的一种优化策略
    //多个线程调用的时候，list,读取的时候：固定的，写入（覆盖）
    //在写入的时候避免覆盖，造成数据问题

    //读写分离
    //CopyOnWriteArrayList相对于Vector优势：效率高低
public class ListTest {
    public static void main(String[] args) {
        // List<String> list=new Vector<>();
        List<String> list= new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
