package exam.pdd2019;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[][] t = new int[n][2];
        for(int i = 0; i < n; i ++){
            t[i][0] = in.nextInt();
            t[i][1] = in.nextInt();
        }
        Arrays.sort(t, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
//        Arrays.stream(t).forEach(e -> System.out.println(e[0] + "  " + e[1]));
        int[] dp = new int[n];
        dp[0] = t[0][1];
        int curMaxIndex = -1;//当前点距离d以上的点中最大的元素下标
        int curMax = Integer.MIN_VALUE;//当前点距离d以上的点中最大的值
        for(int i = 1; i < n; i ++){
            for(int j = curMaxIndex +1; j < i; j++){
                if(t[i][0] - t[j][0] >= d){
                    if(curMax < t[j][1]){
                        curMax = t[j][1];
                        curMaxIndex = j;
                    }
                } else {
                    break;
                }
            }
            dp[i] = Math.max(dp[i-1], curMax + t[i][1]);
        }
//        Arrays.stream(dp).forEach(System.out::println);
        System.out.println(dp[n-1]);

    }


}
