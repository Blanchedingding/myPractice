package exam.zhaohangshixi;

import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] h = new int[n];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            h[i] = in.nextInt();
            if(h[i] < min) min = h[i];
        }
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += h[i] - min;
        }
        if(sum % k == 0){
            System.out.println(sum / k);
        } else {
            System.out.println(sum / k + 1);
        }


    }


}
