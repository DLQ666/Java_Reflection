package com.dlq.java1;

/**
 *@program: Java_Reflection
 *@description:
 *@author: Hasee
 *@create: 2020-07-24 13:40
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface{

    private String name;
    int age;
    public int id;

    public Person(){
    }
    @MyAnnotation(value = "123")
    private Person(String name){
        this.name = name;
    }
    Person(String name , int age){
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是："+nation);
        return nation;
    }

    public String display(String interests,int age)throws NullPointerException,ClassCastException{
        return interests + age;
    }

    @Override
    public void Info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc(){
        System.out.println("我是一个可爱的人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
