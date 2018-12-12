package leetcode;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class Test2 {

    int temp ;

    final Map<String, String> map = new HashMap<>();;

    public Test2(){
        test();
        temp = 2;
//        map
    }

    public void test(){
        System.out.println(this.temp);

    }

    protected void finalize() throws java.lang.Throwable{
        super.finalize();
        System.out.println("Vehicle Object  is disposed");
    }

}

class Test3 extends Test2{

    List<Integer> a = new ArrayList<>();

    public Test3(){
        test();
    }

    public void test(){
        System.out.println(Arrays.toString(a.toArray()));
    }

    public static void main(String[] args) {
        Test3 t = new Test3();
    }
}
