package com.example.liuyong.kotlindemo;

/**
 * Created by liuyong on 2018/6/4.
 */

public class Test {

    public static void main(String... args) {
        int[] array = {1, 2, 3, 4, 5};
        for (int i = 0; i < array.length; i++) {
            System.out.print(i);
        }
        test(new Integer(1));
    }

    public <T> void f(T x) {	//声明无返回值的泛型方法
        System.out.println(x.getClass().getName());
    }

    public static void test(Number a) {

    }

}
