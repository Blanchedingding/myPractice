package exam.qiuzhao.tengxun;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int[] a= new int[n];
        for(int i= 0;i<n;i++){
            a[i] = sc.nextInt();
        }
        int[] b = new int[n];
        for(int i = 0; i < n; i++){
            int max = -1;
            b[i] = 1;
            for(int j = i-1; j >=0;j--){
                if(a[j] > max){
                    b[i] ++;
                    max = a[j];
                }
            }
            max = -1;
            for(int j = i + 1; j < n; j++){
                if(a[j] > max){
                    b[i]++;
                    max = a[j];
                }
            }
        }
        for(int i = 0; i < n - 1; i++){
            System.out.print(b[i]+" ");
        }
        System.out.println(b[n - 1]);
    }
}
