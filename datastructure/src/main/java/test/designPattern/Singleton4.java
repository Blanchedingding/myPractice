package test.designPattern;


public class Singleton4 {

    private final static Singleton4 INSTANCE = new Singleton4();

    private Singleton4(){

    }

    public static Singleton4 getInstance(){
        return INSTANCE;
    }

}
