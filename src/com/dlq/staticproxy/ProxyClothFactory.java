package com.dlq.staticproxy;

/**
 *@program: Java_Reflection
 *@description: 代理类
 *@author: Hasee
 *@create: 2020-07-24 19:39
 */
public class ProxyClothFactory implements ClothFactory{

    private ClothFactory factory;//用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}
