package api;

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
public class Java8NewFeature1 {

    //////////////// 1. Lamda表达式 ////////////////////
    /**
     * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
     * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
     * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
     *
     * 1. lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
     * 2. 我们也可以直接在 lambda 表达式中访问外层的局部变量
     * 3. lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）
     * 4. 在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
     */

    interface MathOperation{
        int operate(int a, int b);
        //默认方法 − 默认方法就是一个在接口里面有了一个实现的方法。
        default int addition(int a, int b){
            return a + b;
        }
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operation(int a, int b, MathOperation mathOperation){
        return mathOperation.operate(a, b);
    }

    ///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        Java8NewFeature1 tester = new Java8NewFeature1();

        //////////////// 1. Lamda表达式 ////////////////////
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { a = a ; return a * b; };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + tester.operation(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operation(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operation(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operation(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

    }
}
