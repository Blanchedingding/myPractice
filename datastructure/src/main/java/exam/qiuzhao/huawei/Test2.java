package exam.qiuzhao.huawei;

import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 3; i++){
            String a = sc.nextLine();
            String b= sc.nextLine();
            result.append(helper(a, b));
        }
        System.out.println(result);
    }

    public static String helper(String a, String b){
        if(a.contains(b)) return "1";
        int m = b.length();
        int n = a.length();
        int[] t1 = new int[52];
        int[] t2 = new int[52];
        for(int i = 0; i < m; i++){
            char c = b.charAt(i);
            if(!a.contains(String.valueOf(c))) return "0";
            if(Character.isLowerCase(c)){
                t1[c-'a' + 26] ++;
            } else {
                t1[c-'A'] ++;
            }
        }
        for(int i = 0; i < n; i++){
            char c = b.charAt(i);
            if(Character.isLowerCase(c)){
                t2[c-'a' + 26] ++;
            } else {
                t2[c-'A'] ++;
            }
        }
        for(int i = 0; i < 52; i++){
            if(t1[i] > t2[i]) return "0";
        }
        for(int i = 1; i < n; i++){
            StringBuilder t = new StringBuilder(a.substring(i));
            if(t.append(a.substring(0, i)).toString().contains(b)) return "1";
        }
        return "0";
    }
}
