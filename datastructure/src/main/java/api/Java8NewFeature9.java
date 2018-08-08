package api;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.util.Base64;
import java.util.UUID;

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
public class Java8NewFeature9 {

    //////////////// 9. Base64 ////////////////////
    /**
     * 在Java 8中，Base64编码已经成为Java类库的标准。
     * Java 8 内置了 Base64 编码的编码器和解码器。
     * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
     * 基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
     * URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
     * MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
     *
     * 内嵌类：
     * 1	static class Base64.Decoder
     * 该类实现一个解码器用于，使用 Base64 编码来解码字节数据。
     *
     * 2	static class Base64.Encoder
     * 该类实现一个编码器，使用 Base64 编码来编码字节数据。
     *
     * 方法：
     * 1	static Base64.Decoder getDecoder()
     * 返回一个 Base64.Decoder ，解码使用基本型 base64 编码方案。
     *
     * 2	static Base64.Encoder getEncoder()
     * 返回一个 Base64.Encoder ，编码使用基本型 base64 编码方案。
     *
     * 3	static Base64.Decoder getMimeDecoder()
     * 返回一个 Base64.Decoder ，解码使用 MIME 型 base64 编码方案。
     *
     * 4    static Base64.Encoder getMimeEncoder()
     * 返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案。
     *
     * 5	static Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)
     * 返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案，可以通过参数指定每行的长度及行的分隔符。
     *
     * 6	static Base64.Decoder getUrlDecoder()
     * 返回一个 Base64.Decoder ，解码使用 URL 和文件名安全型 base64 编码方案。
     *
     * 7	static Base64.Encoder getUrlEncoder()
     * 返回一个 Base64.Encoder ，编码使用 URL 和文件名安全型 base64 编码方案。
     *
     */

    public static void main(String[] args) {
        try {
            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码(基本): " + base64encodedString);
            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

            base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL): " + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME): " + mimeEncodedString);

        }catch(UnsupportedEncodingException e){
            System.out.println("Error :" + e.getMessage());
        }
    }

}
