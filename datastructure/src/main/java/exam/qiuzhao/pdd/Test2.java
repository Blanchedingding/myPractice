package exam.qiuzhao.pdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int n = a.length;
        if(n <= 0) return;
        if(n == 1 ){
            if(a[0].charAt(0) == a[0].charAt(a[0].length() - 1)){
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            return;
        }
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(Character c = 'A'; c <= 'Z'; c++){
            map.put(c, 0);
            map2.put(c, 0);
        }
        for(int i = 0; i < n; i++){
            String s = a[i];
            int l = s.length();
            map.put(s.charAt(0), map.get(s.charAt(0)) + 1);
            map2.put(s.charAt(l -1), map2.get(s.charAt(l-1)) +1);
        }
        for(Character c = 'A'; c <= 'Z'; c++){
            if( ! map.get(c).equals(map2.get(c))){
                System.out.println("false");
                return;
            }
        }

        System.out.println("true");
    }
}
