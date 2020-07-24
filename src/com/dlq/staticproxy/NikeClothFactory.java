package com.dlq.staticproxy;

/**
 *@program: Java_Reflection
 *@description: 被代理类
 *@author: Hasee
 *@create: 2020-07-24 19:40
 */
public class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}
