package exam.shixi.zhaohangshixi;

import java.util.Scanner;

public class Test7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=n;
        if(n<6){
            System.out.println(0);
            return;
        }
        if(n==6){
            System.out.println(1);
            return;
        }
        int count=0;
        int []dp=new int[n-6+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<dp.length;i++){
            dp[i]=dp[i-1]*2;
            if(dp[i]>666666666)
                dp[i]%=666666666;
        }
        while(n>=6){
            count+=dp[m-n];
            if(count>666666666)
                count%=666666666;
            n--;
        }
        System.out.println(count);
    }

}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        long r = 0;
//        for(int i = 6; i <= n; i++){
//            int left = n - i;
//            for(int j = 0; j <= left; j++){
//                r = (r + helper(left, j)) % 666666666;
//            }
//        }
//        System.out.println(r % 666666666);
//    }
//
//    public static long helper(int m, int n) {
//        return m <= n ? factorialSec(m, n) : 0;
//    }
//
//    private static long factorialSec(int m, int n) {
//        long sum = 1;
//        while(m > 0 && n > 0) {
//            sum = sum * n--;
//            m--;
//        }
//        return sum;
//    }


//}
