package test.designPattern;
//1
public class Singleton {
    private Singleton(){

    }

    private static class SingletonHolder{
        private final static Singleton INSTANCE= new Singleton();
    }

    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Singleton2.INSTANCE1.whateverMethod(1));
    }

}

//2，使用enum类型，只有两个实例
enum Singleton2{
    INSTANCE1,
    INSTANCE2;
    public int whateverMethod(int a) {
        return a * 2;
    }
}

