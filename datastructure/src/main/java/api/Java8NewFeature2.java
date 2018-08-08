package api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Java8NewFeature2 {

    ///////////////////////////// 2. 方法引用 ////////////////////////////////////////
    /**
     * 方法引用通过方法的名字来指向一个方法。
     * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
     * 方法引用使用一对冒号 :: 。
     *
     * 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
     */

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Java8NewFeature2 create(final Supplier<Java8NewFeature2> supplier) {
        return supplier.get();
    }

    public static void collide(final Java8NewFeature2 car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Java8NewFeature2 another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    //Supplier< T>接口没有入参，返回一个T类型的对象，类似工厂方法
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    /////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        final Java8NewFeature2 car = Java8NewFeature2.create( Java8NewFeature2::new );
        final List< Java8NewFeature2 > cars = Arrays.asList( car );

        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach( Java8NewFeature2::collide );

        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach( Java8NewFeature2::repair );

        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Java8NewFeature2 police = Java8NewFeature2.create( Java8NewFeature2::new );
        cars.forEach( police::follow );

        /////////////////////一个简单例子///////////////////////
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
