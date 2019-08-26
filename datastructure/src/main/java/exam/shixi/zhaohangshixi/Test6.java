package exam.shixi.zhaohangshixi;

import java.util.Arrays;
import java.util.Scanner;

public class Test6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] a = new int[2 * n];
        for(int i =0; i < 2*n; i++){
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        double t = (double)w / (3*n);

        if(a[n] >= 2 * t && a[0] >= t){
            System.out.println(String.format("%.6f",(double)w));
        } else {
            if(a[n] / 2 <= a[0]){
                double r = (double)a[n] / 2 * 3 * n;
                System.out.println(String.format("%.6f",r));
            }else {
                double r = (double)a[0]* 3 * n;
                System.out.println(String.format("%.6f",r));
            }
        }
    }
}
