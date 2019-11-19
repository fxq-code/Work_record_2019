package org.cboard.genWord.genImg.utils;

import com.github.abel533.echarts.AbstractData;
import com.github.abel533.echarts.data.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 处理有关比较棘手的相关问题
 * */
public class ProxyInvokerHandlerUtil {


    /**
     * 主要处理动态参数的调用
     * 注意：这里需要直接将处理的实例类传入，即可构建相应的数据类的相关信息
     * */
    public  static   void processDataInvoker(AbstractData iclazz,Object[] arr) {
      //  System.out.println("class= " + iclazz.getClass().getName() + " arr=" + arr.length);
        Class clazz = iclazz.getClass();//主要处理有关数据的设置的相关的方法
        try {
            AbstractData abstractData = (AbstractData) iclazz;
            Method method = clazz.getMethod("data", Object[].class);
           // System.out.println(method);
            Object[] objects = {arr};
            method.invoke(abstractData, objects);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    /**
     * 主要处理动态参数的调用
     * */
    public  static   void processDataInvoker2 (Class<?> clazz,AbstractData iclazz,Object[] arr){
        System.out.println("class= "+clazz.getName()+" arr="+arr.length);
        //Class mclazz=AbstractData.class;//主要处理有关数据的设置的相关的方法

        try {
            AbstractData abstractData= (AbstractData) Class.forName(clazz.getName()).newInstance();
            Method method=clazz.getMethod("data",Object[].class);
            System.out.println(method);
            Object[] objects={arr};
//            for (int i = 0; i < objects.length; i++) {
//                System.out.print(objects[i]+" ");
//            }
            method.invoke(abstractData,objects);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 主要处理动态参数的调用
     * */
    public  static   void processDataInvoker1(Class<?> clazz,Object[] arr){
        System.out.println("class= "+clazz+" arr="+arr.length);
        //Class mclazz=AbstractData.class;//主要处理有关数据的设置的相关的方法

        try {
            AbstractData abstractData=(AbstractData)clazz.newInstance();
            Method method=clazz.getMethod("data",Object[].class);
            System.out.println(method);
            Object[] objects={arr};
//            for (int i = 0; i < objects.length; i++) {
//                System.out.print(objects[i]+" ");
//            }
            method.invoke(abstractData,objects);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

}
