package com.drm.sample.web;

public class Test {
    public static void main(String[] args) {
        try {
            method1();
            System.out.println("exception didn't accur");
        } catch (MyException1  | MyException2 e) {  // syntax '|' or in this case
            System.out.println("exception was caught: " + e.getClass().getSimpleName());
        }  finally {
            System.out.println("finally executed");
        }
        System.out.println("method 'main' ended");
    }

    private static void method1() throws MyException1, MyException2 {
        System.out.println("method1 started");
        method2();
        System.out.println("method1 completed");
    }

    private static void method2() throws MyException1, MyException2 {
        System.out.println("method2 started");
        method3();
        System.out.println("method2 completed");
    }

    private static void method3() throws MyException1, MyException2 {
        System.out.println("method3 started");
        if (System.currentTimeMillis() % 2 == 0) {
            throw new MyException1();
        } else {
            throw new MyException2();
        }
    }
}
