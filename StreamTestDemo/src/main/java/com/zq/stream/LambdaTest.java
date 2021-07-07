package com.zq.stream;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
@TestClass
public class LambdaTest {
    public static void main(String[] args) {
        String userName = "true";
        //isPresent  值为空返回false  不为空返回true
        if (Optional.ofNullable(userName).isPresent()) {
            System.out.println("userName不为空");
        }
        //orElse 如果前面参数值为空则返回定义的值
        String testData = Optional.ofNullable(userName).orElse("测试");
        System.out.println("data:" + testData);

        String dd = Optional.ofNullable(userName).filter(e -> {
            int length = userName.length();
            //判断参数是否为true
            boolean flag = Boolean.parseBoolean(e.substring(0, length));
            System.out.println(flag);
            return flag;
        }).orElse("dd");

        //如果userName不为空  就会调用appect方法  ifPresent就会打印输出语句
        Optional.ofNullable(userName).ifPresent(System.out::print);
    }

    @Test
    public void classTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.zq.stream.ListDto");
        //反射无参
//        Object instance = aClass.newInstance();
//        System.out.println(instance);

        //反射有参赋值
//        Constructor<?> constructor = aClass.getConstructor(String.class, Integer.class);
//        System.out.println(constructor.newInstance("zhangsan",23));

        //反射  给属性赋值
//        ListDto listDto = (ListDto) aClass.newInstance();
//        Object listDto = aClass.newInstance();
//        Field name = aClass.getDeclaredField("name");
//        name.setAccessible(true);
//        name.set(listDto,"张三");//set(对象，值)
//        System.out.println(listDto);

        Method methodTest = aClass.getDeclaredMethod("methodTest", String.class);
        methodTest.setAccessible(true);
        Transactional declaredAnnotation = methodTest.getDeclaredAnnotation(Transactional.class);//获取是否有事务（根据参数传的类去判断是获取什么注解）
        System.out.println(declaredAnnotation);
        Object instance = aClass.newInstance();
        methodTest.invoke(instance,"反射方法调用");
    }

    @Test
    public void testClass() throws ClassNotFoundException {
        Class<?> classz = Class.forName("com.zq.stream.LambdaTest");
        Field[] fields = classz.getDeclaredFields();
        for (Field field:fields) {
            if (field.isAnnotationPresent(TestClass.class)){
                System.out.println("使用了注解");
            }else {
                System.out.println("false");
            }
        }
    }
}
