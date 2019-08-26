package exam.shixi.pinduoduo;


import java.util.HashMap;
import java.util.Map;

/**
 * 循环小数开始循环的位置和循环长度
 */
public class Test1 {

    public static void helper(int a, int b){
        a = a % b;
        Map<Integer, Integer> v = new HashMap<>();
        int index = 0;
        for(;;){
            v.put(a,index);
            a *= 10;
            a = a % b;
            if(a == 0){
                System.out.println(index + " 0");
                break;
            }
            if(v.containsKey(a) ){
                System.out.println(v.get(a) +" "+(v.size() - v.get(a)));
                break;
            }
            index++;
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
        helper(5, 5);
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//
//        HashMap<Integer, Integer> mod = new HashMap<>();
//        int num = 0;
//        while(1==1){
//            a = a%b;
//            if(a==0){
//                System.out.println(num+" "+"0");
//                break;
//            }
//            if(mod.containsKey(a)){
//                System.out.println(mod.get(a)+" "+(mod.size()-mod.get(a)));
//                break;
//            }
//            mod.put(a, num);
//            a = a*10;
//            num++;
//        }
//    }

}
