package exam.pdd2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int left = 0, right = 0;
        String s1 = in.nextLine();
        char[] c1 = s1.toCharArray();
        for(int i = 0; i < c1.length; i++){
            if(c1[i] == '(') left ++;
            else right ++;
        }
        String s2 = in.nextLine();
        char[] c2 = s2.toCharArray();
        for(int i = 0; i < c2.length; i++){
            if(c2[i] == '(') left ++;
            else right ++;
        }
        if(left != right){
            System.out.println(0);
        } else {
            int count = helper(0, left);

            System.out.println(count % (Math.pow(10, 9) + 7));
        }
    }


    public static int helper(int count, int left){
        if(left == 0) return count;
        return helper(count + 3, left - 1);

    }
}
