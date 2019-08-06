package com.king.newfeatures.optional;

import java.util.Optional;

/***
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 *
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 *
 * Optional 类的引入很好的解决空指针异常。
 */
public class TestOptional {


    public static void main(String args[]){

        TestOptional testOptional = new TestOptional();


        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        testOptional.sum(a,b);
        //System.out.println(a+"-"+b);
        //System.out.println(testOptional.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        //System.out.println("a.get():"+a.get());
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        System.out.println(value2);
        return value1 + value2;
    }
}
