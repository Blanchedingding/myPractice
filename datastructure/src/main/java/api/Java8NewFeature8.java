package api;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.*;

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
public class Java8NewFeature8 {

    //////////////// 8. 日期时间 API ////////////////////

    /**
     *Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
     * 在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
     * 非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
     * 设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
     * 时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
     * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
     * Local(本地) − 简化了日期时间的处理，没有时区的问题。
     * Zoned(时区) − 通过制定的时区处理日期时间。
     * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
     *
     */

    ///////////////////////////// 本地化日期时间 API //////////////////////////////////////////////////

    //LocalDate/LocalTime 和 LocalDateTime 类可以在处理时区不是必须的情况。
    public void testLocalDateTime(){
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + " " + month.getValue() +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    ////////////////////////////// 使用时区的日期时间API //////////////////////////////////////////
    public void testZonedDateTime(){

        //获取当前时区时间2018-08-08T14:45:13.070+08:00[Asia/Shanghai]
        ZonedDateTime date0 = ZonedDateTime.now();
        System.out.println(date0);

        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        // date1: 2015-12-03T10:15:30+08:00[Asia/Shanghai]
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }

    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Java8NewFeature8 test = new Java8NewFeature8();
        test.testLocalDateTime();
        System.out.println("//////////////////");
        test.testZonedDateTime();
    }

}
