package leetcode;

/**
 * 抽象类和普通类的主要有三点区别：
 *
 * 1）抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public。
 *
 * 2）抽象类不能用来创建对象；
 *
 * 3）如果一个类继承于一个抽象类，则子类必须实现父类的抽象方法。如果子类没有实现父类的抽象方法，则必须将子类也定义为为abstract类。
 */
public abstract class AbstartClassTest {

    public AbstartClassTest(){

    }

    static final int i = 0;

    static int j = 1;

    final int k = 2;

    int p = 3;

    static int one(){
        return 1;
    }

    abstract int two(int a);

    int three(int b){
        return 4;
    }

}
