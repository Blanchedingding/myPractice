package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test2 {

    int temp ;

    public Test2(){
        test();
        temp = 2;
    }

    public void test(){
        System.out.println(this.temp);
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
