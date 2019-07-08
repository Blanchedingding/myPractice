package leetcode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

public enum EnumTest {
    SPRING(1);
    public int seq;
    // 枚举类的构造函数必须是private的
    EnumTest(int i){
        this.seq = i;
    }
}

class Test1{



    public static void main(String[] args) {
//        String s  = "";
//        s = null;
//        System.out.println(Objects.equals(s, "e"));

//        Apple a = new Apple();
//        Apple b = new Apple();
//        Banana c = new Banana();
//        Fruit d = new Fruit();
//        Plate<? super Fruit> p=new Plate<Fruit>(new Apple());
//        //存入元素正常
//        p.set(new Fruit());
//        p.set(new Apple());
//        Object t = p.get();
//
//        Map<Integer, String> map = new HashMap<>(16);
//        map.forEach((k, v) ->{
//
//        });

//        CompletionService cs = new ExecutorCompletionService();
//        Executor
//        Executors.newSingleThreadExecutor();
//        ThreadPoolExecutor tpe = new ThreadPoolExecutor();




//        List<String> a = new ArrayList<String>();
//        a.add("1");
//        a.add("2");
//        for (String temp : a) {
//            if("1".equals(temp)){
//                a.add(temp);
//            }
//        }

//        Instant i = new Instant();
        System.out.println(Instant.now().toString());

//        ScheduledExecutorService e = Executors.newScheduledThreadPool(2);

    }
}
class Plate<T>{
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}

class Fruit{

}

class Apple extends Fruit{

}

class Banana extends Fruit{

}