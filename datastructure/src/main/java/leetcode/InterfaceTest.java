package leetcode;

/**
 * 接口注意：
 *
 * 声明的变量都是静态变量并且是final的，所以子类无法修改，并且是固定值不会因为实例而变化
 * 接口中能有静态方法，不能有普通方法，普通方法需要用defalut添加默认实现
 * 接口中的变量必须实例化
 * 接口中没有静态代码块、普通变量、普通代码块、构造函数
 *
 * 接口方法不能是final的，和abstract方法矛盾
 */
public interface InterfaceTest {

    static final int i = 1;
    //必须初始化
    static final Test t = new Test();

    public abstract int two();

    //java8开始，可以有静态方法和默认方法
    //1. 实现类会继承接口中的default方法
    //2. 如果一个类同时实现接口A和B，接口A和B中有相同的default方法，这时，该类必须重写接口中的default方法
    //3. 如果子类继承父类，父类中有b方法，该子类同时实现的接口中也有b方法（被default修饰），
    // 那么子类会继承父类的b方法而不是继承接口中的b方法
    default int three(){
        return 1;
    }

    public static int one(){
        return 1;
    }

}
