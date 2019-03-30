package exam.pdd2019;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++){
            b[i] = in.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        long s = 0;
        for(int i = 0; i < n; i++){
            s += a[i] * b[n-1-i];
        }
        System.out.println(s);
//        System.out.println(a[0] + a[1] + a[2]);
//        System.out.println(b[0] + b[1] + b[2]);
    }
}
