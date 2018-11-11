package leetcode;

class Thing {
    public Thing(int i) {

    }
}

class SomeOtherClass {
    static int func() {
        return Math.round(12L);
    }
}

//错误示范！！！！！！！！！
//public class MyThing extends Thing {
//    private final int arg;
//
//    public MyThing() {
//        //父类构造函数还没调用完不能初始化子类的实例变量
//        super(arg = SomeOtherClass.func()); //编译失败
//    }
//
//}

//正确方法：私有构造器捕获——交替构造器调用机制（alternate constructor invocation）
public class MyThing extends Thing {
    private final int arg;

    public MyThing() {
        this(SomeOtherClass.func());
    }

    private MyThing(int i) {
        super(i);
        arg = i;
    }
}
