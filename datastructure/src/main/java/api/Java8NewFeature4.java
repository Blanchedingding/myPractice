package api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Java8 新增了非常多的特性，我们主要讨论以下几个：
 *
 * Lambda 表达式 − Lambda允许把函数作为一个方法的参数（函数作为参数传递进方法中。
 *
 * 方法引用 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。
 *              与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 *
 * 默认方法 − 默认方法就是一个在接口里面有了一个实现的方法。
 *
 * 新工具 − 新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。
 *
 * Stream API −新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
 *
 * Date Time API − 加强对日期与时间的处理。
 *
 * Optional 类 − Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
 *
 * Nashorn, JavaScript 引擎 − Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。
 */
public class Java8NewFeature4 {

    ///////////////////////////// 4. 默认方法 ////////////////////////////////////////

    /**
     * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
     * 我们只需在方法名前面加个default关键字即可实现默认方法。
     *
     * 为什么要有这个特性？
     * 首先，之前的接口是个双刃剑，好处是面向抽象而不是面向具体编程，
     * 缺陷是，当需要修改接口时候，需要修改全部实现该接口的类，
     * 目前的java 8之前的集合框架没有foreach方法，通常能想到的解决办法是在JDK里给相关的接口添加新的方法及实现。
     * 然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。
     * 所以引进的默认方法。他们的目的是为了解决接口的修改与现有的实现不兼容的问题。
     *
     */
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }

    //Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}
