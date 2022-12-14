package com.function;

import java.util.function.Supplier;

/**
 * supplier:供给型接口：没有参数，只有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        /*Supplier supplier=new Supplier() {
            @Override
            public Integer get() {
                return 1024;
            }
        };*/

        Supplier supplier=()->{
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
