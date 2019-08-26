package exam.shixi.zhaohangshixi;

import java.util.Scanner;

public class Test3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        long sum = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        int c1 = 0, c2 = 0;
        for(int i=0; i < n; i++){
            a[i] = in.nextInt();
            sum += Math.abs(a[i]);
            if (a[i] > max)  max = a[i];
            if (a[i] < min)  min = a[i];
            if (a[i] <= 0)    c1++;
            if (a[i] >= 0)    c2++;
        }
        if (n == 1){
            System.out.println(a[0]);
        }else {
            if (c1 == n)
                sum += 2 * max;
            else if (c2 == n)
                sum -= 2 * min;
            System.out.println(sum);
        }
    }

}


