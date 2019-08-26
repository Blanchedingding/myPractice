package exam.qiuzhao.huawei;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        int M = 0, N = 1;
        double minGap = Double.MAX_VALUE;
        for(int n = 1; n <= 10000; n++){
            int m = (int)(n * d) - 1;
            double t = Math.abs(((double)m / (double)n) - d) ;
            if(t < minGap){
                minGap = t;
                M = m;
                N = n;
            }
            m ++;
            t = Math.abs(((double)m / (double)n) - d) ;
            if(t < minGap){
                minGap = t;
                M = m;
                N = n;
            }
            m ++;
            t = Math.abs(((double)m / (double)n) - d) ;
            if(t < minGap){
                minGap = t;
                M = m;
                N = n;
            }
        }
        System.out.println(M + " " + N);
    }


}
