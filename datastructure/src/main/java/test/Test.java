package test;

public class Test {

    static int i = 1;
    int j = 2;

    static{
        //只能给后面的静态变量赋值，不能访问，23333
//        System.out.println(k);
        k = 3;
    }


    {
        k = 3;
    }

    static int k;

    public Test(){

    }

    public static void main(String[] args) {
        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;
        Long g=3L;
        Long h = 2L;
        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        System.out.println(g.equals(a+h));
    }
}
