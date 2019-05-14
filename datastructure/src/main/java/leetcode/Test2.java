package leetcode;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class Test2 {

    int temp ;

    protected int h = 0;

    private void getk(){

    }
    final Map<String, String> map = new HashMap<>();

//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(TestEnum.values()) + "  " + TestEnum.AE.ordinal() + "  " + TestEnum.AE.toString());
//    }

    public Test2(long j){

    }

    public void op(Double... a){

    }

    public Test2(){
        this(2);
        int[] a = {1,2,3};
        System.out.println(Arrays.toString(a));

//        label:
//        for(int i = 0; i < 4; i++){
//            if(i == 2){
//                continue label;
//            }
//        }
//        test();

        String s = "344";
        switch(s){
            case "344":{
                System.out.println("233");
                break;
            }
        }
        temp = 2;
        ArrayList<Integer> b = new ArrayList<>();
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
        h = 9;
    }

    public void test(){
        System.out.println(Arrays.toString(a.toArray()));
    }

    public static void main(String[] args) {
        Test3 t = new Test3();
    }
}

enum TestEnum{
    AE,BE,CE;

}
