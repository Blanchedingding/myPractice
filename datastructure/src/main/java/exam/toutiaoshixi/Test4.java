package exam.toutiaoshixi;

import java.util.Arrays;
import java.util.Scanner;

public class Test4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        double[] l = new double[n];
        for(int i = 0; i < n; i++){
            l[i] = in.nextInt();
        }
        double ans = 0;
        int loc;
        int[] num = new int[n];
        Arrays.fill(num, 1);
        double[] length = new double[n];
        for(int i = 0; i < n; i ++){
            length[i] = l[i];
        }
        for(int i=0;i<m;++i) {
            loc = getMax(length);//取length的最大值的下标
            ans = length[loc];//length最大值的角标（序号）
            ++num[loc];
            length[loc] = l[loc] / num[loc];
            System.out.println(ans + "  " + loc);
            System.out.println(Arrays.toString(length));
            System.out.println(Arrays.toString(num));
            System.out.println("---------");
        }
        System.out.println(ans);
    }

    public static int getMax(double[] length){
        double max = Integer.MIN_VALUE;
        int r = -1;
        for(int i = 0; i < length.length; i++){
            if(length[i] > max){
                max = length[i];
                r = i;
            }
        }
        return r;
    }

}
