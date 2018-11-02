package leetcode;


import java.util.ArrayList;
import java.util.List;

public class Test2 {

    static final int _init = 1;

//    public static String test(List<Integer> args){
//        System.out.println("hello char...");
//        return "";
//    }
//
    public static int test(List<String> args){
        System.out.println("hello char...");
        final int _init = 1;
        return 0;
    }

    class A{
        public void b(int a){

        }

        public  void main(String[] args) {

        }
    }


    public static void test(int... args){
        System.out.println("hello char...");
    }

//    public static void test(Character args){
//        System.out.println("hello                 char...");
//    }

    //不能与test(char... args)同时存在
//    public static void test(Character... args){
//        System.out.println("hello char...");
//    }
//
    //不能与int...同时存在
//    public static void test(Object... args){
//        System.out.println("hello char...");
//    }

    public static void main(String[] args) {
//        test('a');
        List list = new ArrayList();
        list.add("qqyumidi");
        list.add("corn");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // 1
            System.out.println("name:" + name);
        }



//        List<Integer> l = [1,2,3];
    }
}
