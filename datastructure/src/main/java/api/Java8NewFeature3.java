package api;

import java.util.ArrayList;
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
public class Java8NewFeature3 {

    ///////////////////////////// 3. 函数式接口 ////////////////////////////////////////
    /**
     * 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
     * 函数式接口可以被隐式转换为lambda表达式。
     * 函数式接口可以现有的函数友好地支持 lambda。
     * JDK 1.8之前已有的函数式接口:
     * java.lang.Runnable
     * java.util.concurrent.Callable
     * java.security.PrivilegedAction
     * java.util.Comparator
     * java.io.FileFilter
     * java.nio.file.PathMatcher
     * java.lang.reflect.InvocationHandler
     * java.beans.PropertyChangeListener
     * java.awt.event.ActionListener
     * javax.swing.event.ChangeListener
     * JDK 1.8 新增加的函数接口：
     * java.util.function
     * java.util.function 它包含了很多类，用来支持 Java的 函数式编程，该包中的函数式接口有：
     * 1	BiConsumer<T,U>: 代表了一个接受两个输入参数的操作，并且不返回任何结果
     * 2	BiFunction<T,U,R>: 代表了一个接受两个输入参数的方法，并且返回一个结果
     * 3	BinaryOperator<T>: 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果
     * 4	BiPredicate<T,U>: 代表了一个两个参数的boolean值方法
     * 5	BooleanSupplier: 代表了boolean值结果的提供方
     * 6	Consumer<T>: 代表了接受一个输入参数并且无返回的操作
     * 7	DoubleBinaryOperator: 代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。
     * 8	DoubleConsumer: 代表一个接受double值参数的操作，并且不返回结果。
     * 9	DoubleFunction<R>: 代表接受一个double值参数的方法，并且返回结果
     * 10	DoublePredicate: 代表一个拥有double值参数的boolean值方法
     * 11	DoubleSupplier: 代表一个double值结构的提供方
     * 12	DoubleToIntFunction: 接受一个double类型输入，返回一个int类型结果。
     * 13	DoubleToLongFunction: 接受一个double类型输入，返回一个long类型结果
     * 14	DoubleUnaryOperator: 接受一个参数同为类型double,返回值类型也为double 。
     * 15	Function<T,R>: 接受一个输入参数，返回一个结果。
     * 16	IntBinaryOperator: 接受两个参数同为类型int,返回值类型也为int 。
     * 17	IntConsumer: 接受一个int类型的输入参数，无返回值 。
     * 18	IntFunction<R>: 接受一个int类型输入参数，返回一个结果 。
     * 19	IntPredicate：接受一个int输入参数，返回一个布尔值的结果。
     * 20	IntSupplier: 无参数，返回一个int类型结果。
     * 21	IntToDoubleFunction: 接受一个int类型输入，返回一个double类型结果 。
     * 22	IntToLongFunction: 接受一个int类型输入，返回一个long类型结果。
     * 23	IntUnaryOperator: 接受一个参数同为类型int,返回值类型也为int 。
     * 24	LongBinaryOperator: 接受两个参数同为类型long,返回值类型也为long。
     * 25	LongConsumer: 接受一个long类型的输入参数，无返回值。
     * 26	LongFunction<R>: 接受一个long类型输入参数，返回一个结果。
     * 27	LongPredicate: R接受一个long输入参数，返回一个布尔值类型结果。
     * 28	LongSupplier: 无参数，返回一个结果long类型的值。
     * 29	LongToDoubleFunction: 接受一个long类型输入，返回一个double类型结果。
     * 30	LongToIntFunction: 接受一个long类型输入，返回一个int类型结果。
     * 31	LongUnaryOperator: 接受一个参数同为类型long,返回值类型也为long。
     * 32	ObjDoubleConsumer<T>: 接受一个object类型和一个double类型的输入参数，无返回值。
     * 33	ObjIntConsumer<T>: 接受一个object类型和一个int类型的输入参数，无返回值。
     * 34	ObjLongConsumer<T>: 接受一个object类型和一个long类型的输入参数，无返回值。
     * 35	Predicate<T>: 接受一个输入参数，返回一个布尔值结果。
     * 36	Supplier<T>: 无参数，返回一个结果。
     * 37	ToDoubleBiFunction<T,U>:接受两个输入参数，返回一个double类型结果
     * 38	ToDoubleFunction<T>: 接受一个输入参数，返回一个double类型结果
     * 39	ToIntBiFunction<T,U>: 接受两个输入参数，返回一个int类型结果。
     * 40	ToIntFunction<T>: 接受一个输入参数，返回一个int类型结果。
     * 41	ToLongBiFunction<T,U>: 接受两个输入参数，返回一个long类型结果。
     * 42	ToLongFunction<T>: 接受一个输入参数，返回一个long类型结果。
     * 43	UnaryOperator<T>: 接受一个参数为类型T,返回值类型也为T。
     */
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");

        // 传递参数 n
        eval(list, n->true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        eval(list, n-> n%2 == 0 );

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n-> n > 3 );
    }
}
