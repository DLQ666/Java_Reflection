package com.dlq.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflection（反射）是被视为<动态语言>的关键，反射机制允许程序在执行期借助于Reflection API
 * 取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。

 * 加载完类之后，在堆内存的<方法区>中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就
    包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。《这个对象就像一面镜子，透过这个镜子看到
    类的结构，所以，我们形象的称之为：反射。》

 补充：
    1、动态语言：是一类在<运行时>可以改变其结构的语言：例如新的函数、对象、甚至代码可以被引进，
       已有的函数可以被删除或是其他结构上的变化。通俗点说就是<在运行时代码可以根据某些条件改变自身结构>
       主要动态语言：Object-C、C#、JavaScript、PHP、Python、Erlang
    2、静态语言：与动态语言相对应的，<运行时结构不可变的语言就是静态语言。如Java、C、C++>

    Java不是动态语言，但Java可以称之为“准动态语言”。即Java有一定的动态性，我们可以利用反射机制、字节码操作获得
    类似动态语言的特性。
    Java的动态性让编程的时候更加灵活！



 *@program: Java_Reflection
 *@description:
 *@author: Hasee
 *@create: 2020-07-23 18:04
 */
public class ReflectionDemo {

    //反射之前，对于Person的操作
    @Test
    public void test1(){

        //1、创建Person类的对象
        Person p1 = new Person("Tom",12);

        //2、通过对象，调用其内部的属性方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如：name、showNation以及私有的构造器。
    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        //1、通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2、通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("********************************************");

        //通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有构造器
        Constructor<Person> cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = cons1.newInstance("Jerrt");
        System.out.println(p1);
        //调用私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);
        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);

    }

    //疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
    //建议：直接new的方式。
    //什么时候会使用：反射的方式。 反射的特征：动态性
    //疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    //不矛盾。

    /**
    关于java.lang.Class类的理解
    1、类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
     接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程成为类的加载。
     加载到内存中的类，我们就称为运行时类，此运行时类，就作为Class的一个实例。

    2、换句话说，Class的实例就对应着一个运行时类。
    3、加载到内存中的运行时类，会缓存一定的时间。在此时间内，我们可以通过不同的方式来获取此运行时类。

    //万物皆对象   对象.xxxx,File,URL,反射，前端、数据库操作
     */

    //获取Class的实例的方式
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName(String classPath) ------>经常使用
        Class clazz3 = Class.forName("com.dlq.java.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：实用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.dlq.java.Person");
        System.out.println(clazz4);
        System.out.println(clazz1 == clazz4);
    }

    //Class实例可以是那些结构的说明：
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要元素类型与维度一样（上边两个都是一维数组），就是同一个Class
        System.out.println(c10 == c11);
    }

}
