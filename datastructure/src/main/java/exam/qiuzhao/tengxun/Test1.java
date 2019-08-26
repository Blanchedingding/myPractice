package exam.qiuzhao.tengxun;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(helper(s));
    }

    public static String helper(String s){
        int n = s.length();
        if(n <= 0) return "";
        int left = s.indexOf('[');
        if(left <= -1) return s;
        int h = 1;
        int right = -1;
        for(int j = left + 1; j < n; j++){
            if(s.charAt(j) == '['){
                h ++;
            } else if(s.charAt(j) == ']'){
                h --;
            }
            if(h == 0){
                right = j;
                break;
            }
        }
        StringBuilder result = new StringBuilder(s.substring(0, left));

        int num = 0;
        int k = left + 1;
        for(; k < right; k++){
            if(s.charAt(k) == '|'){
                break;
            } else {
                num = num * 10 + (s.charAt(k) - 48);
            }
        }
        String mid = helper(s.substring(k + 1, right));
        for(int j = 0; j < num; j++){
            result.append(mid);
        }
        result.append(helper(s.substring(right + 1)));
        return result.toString();
    }


}
