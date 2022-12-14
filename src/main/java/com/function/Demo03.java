package com.function;

import java.util.function.Consumer;

/**
 * Consumer消费者接口：只有输入，没有输出
 */
public class Demo03 {
    public static void main(String[] args) {
        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };*/
        Consumer<String> consumer=(str)->{
            System.out.println(str);
        };
        consumer.accept("zhang");
    }
}
