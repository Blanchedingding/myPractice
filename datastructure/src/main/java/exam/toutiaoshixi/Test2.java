package exam.toutiaoshixi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test2 {

    //字符串中不能出现连续3个，两组连续2个的第二组要删掉一个
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(in.next());
        }
        List<String> result = new ArrayList<>();
        for(String s : list){
            String r = helper1(s);
            result.add(helper2(r));
        }
        for(String s : result){
            System.out.println(s);
        }

    }

    public static String helper1 (String s){
        char[] c = s.toCharArray();
        int repeat = 0;
        char p = '%';
        int n = c.length;
        List<Integer> u = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(p == '%'){
                p = c[i];
                repeat = 1;
            } else {
                if(p == c[i]){
                    repeat ++;
                } else {
                    p = c[i];
                    repeat = 1;
                }
            }
            if(repeat >= 3){
                u.add(i);
                repeat --;
            }
        }
        String y = "";
        for(int i = 0;i < n; i++){
            if(! u.contains(i)){
                y += c[i];
            }
        }
        return y;
    }

    public static String helper2 (String s){
        char[] c = s.toCharArray();
        int repeat1 = 1;
        char p1 = c[0];
        int repeat2 = 0;
        char p2 = '%';
        int n = c.length;
        List<Integer> u = new ArrayList<>();
        for(int i = 1; i < n; i++){
            if(p2 == '%' && c[i] != p1){
                repeat2 = 1;
                p2 = c[i];
            }else if(p2 == '%' && c[i] == p1){
                repeat1 ++;
            } else {
                if(c[i] == p2){
                    repeat2 ++;
                } else {
                    p1 = p2;
                    repeat1 = repeat2;
                    p2 = c[i];
                    repeat2 = 1;
                }
            }
            if(repeat1 == 2 && repeat2 == 2){
                u.add(i);
                repeat2 --;
            }
        }
        String y = "";
        for(int i = 0;i < n; i++){
            if(! u.contains(i)){
                y += c[i];
            }
        }
        return y;
    }
}
