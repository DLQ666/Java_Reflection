package com.dlq.dynamicproxy;

/**
 *@program: Java_Reflection
 *@description: 被代理类
 *@author: Hasee
 *@create: 2020-07-24 19:47
 */
public class SuperMan implements Human {
    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
