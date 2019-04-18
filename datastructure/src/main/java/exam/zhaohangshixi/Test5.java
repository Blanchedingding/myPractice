package exam.zhaohangshixi;

import java.util.Scanner;

import java.util.Arrays;


public class Test5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] cups = new int[n];
        for(int i = 0; i < n; i++){
            cups[i] = sc.nextInt();
        }

        int count = 0;
        for(int i = 0;i < n;i++){
            //检查每个数字并缩放到2的幂次上
            while(!check(cups[i])){
                cups[i] = cups[i]/2;
                count++;
            }
        }

        //排序并寻找中位数
        Arrays.sort(cups);
        int middle = cups[cups.length / 2];
        //向中位数靠拢
        for(int i = 0;i < n; i++){
            while(cups[i] < middle){
                cups[i] = cups[i] * 2;
                count++;
            }
            while(cups[i] > middle){
                cups[i] = cups[i] / 2;
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean check(int val){
        return (val & -val) == val;
    }


}