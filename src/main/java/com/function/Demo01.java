package com.function;

import java.util.function.Function;

/**
 * Function:函数型接口:有一个输入参数，有一个输出
 * 只要是函数型接口，就可以用lambda表达式
 */
public class Demo01 {
    public static void main(String[] args) {
        //工具类：输出输入的值
        /*Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };*/

        //用lambda表达式简化
        Function function=(str)->{return str;};
        System.out.println(function.apply("algb"));
    }
}
