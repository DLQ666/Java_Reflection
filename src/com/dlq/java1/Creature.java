package com.dlq.java1;

import java.io.Serializable;

/**
 *@program: Java_Reflection
 *@description:
 *@author: Hasee
 *@create: 2020-07-24 13:40
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }
}
