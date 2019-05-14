package leetcode;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Super {

    static class InnerSub{

    }

    public int i = 0;
    public int getI(){
//        System.out.println("super.geti");
        return i;
    }
}

class Sub2 extends Super{
    public int i = 2;
    public Sub2(){

    }

    class Inner2{

    }
}

class Sub extends Super{

    public int i = 1;
    public int getI(){
//        System.out.println("sub.geti");
        return i;
    }
    public int getSuperI(){
//        System.out.println("sub.super.geti");
        return super.i;
    }

    public static void main(String[] args) {
//        Super super1=new Sub();
//        System.out.println("sup.field="+super1.i+",sup.getField="+super1.getI());
//        Sub sub2 = (Sub)super1;
//        System.out.println("--sub2.fieled=" + sub2.i + "---sub2.getField="+sub2.getI());
//        Sub sub=new Sub();
//        System.out.println("sub.field="+sub.i + ",sub.getField="+sub.getI() + ",sub.getSuperField="+sub.getSuperI());

        Super.InnerSub inner = new Super.InnerSub();

        Sub2.Inner2 inner2 = new Sub2().new Inner2();

//        Super sub1 = new Sub();
//        Super sub2 = new Sub2();
//        List<Super> list = Arrays.asList(new Super[]{sub1, sub2});
//        for(Super s : list){
//            System.out.println(s.getClass().getName());
//            System.out.println("s.i=" + s.i);
//            System.out.println("s.getI()=" + s.getI());
//            System.out.println();
//        }
    }
}
