package com.dlq.java;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 *
 *@program: Java_Reflection
 *@description:
 *@author: Hasee
 *@create: 2020-07-23 22:06
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@279f2327
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);//为null

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);//为null
    }
    /*
        Properties：用来读取配置文件。
     */
    @Test
    public void test2() throws Exception {

        Properties properties = new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc1.properties");
//        FileInputStream fis = new FileInputStream("D:\\IDEA_workspace\\Java_Reflection\\src\\jdbc1.properties");
        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
        properties.load(fis);

        //读取配置文件的方式二：ClassLoader
        //配置文件默认识别为：当前module的src下
//        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
//        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user);
        System.out.println(password);
    }
}
