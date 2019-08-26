package exam.qiuzhao.pdd;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input1 = sc.nextLine().split(" ");
        int n1 = input1.length;
        int[] A = new int[n1];
        for(int i = 0; i < n1; i++){
            A[i] = Integer.valueOf(input1[i]);
        }
        String[] input2 = sc.nextLine().split(" ");
        int n2 = input2.length;
        int[] B = new int[n2];
        for(int i = 0; i < n2; i++){
            B[i] = Integer.valueOf(input2[i]);
        }

        int index = 0;
        for(int i = 1; i < n1; i++){
            if(A[i] <= A[i - 1]){
                index = i;
            }
        }

        int min = index > 0? A[index - 1]: Integer.MIN_VALUE;
        int max = index < n1 - 1? A[index  +1] : Integer.MAX_VALUE;

        if(min != Integer.MIN_VALUE && max != Integer.MAX_VALUE && max <= min){
            index = index - 1 ;
            max =  A[index  +1];
            min = index > 0? A[index - 1]: Integer.MIN_VALUE;
        }

        int m = Integer.MIN_VALUE;
        for(int i = 0; i < n2; i++){
            if(B[i] > m && B[i] > min &&  B[i] < max){
                m = B[i];
            }
        }
        if(m == Integer.MIN_VALUE) {
            if(index > 0 && A[index] == A[index - 1]){
                index = index - 1;
                min = index > 0? A[index - 1]: Integer.MIN_VALUE;
                max = index < n1 - 1? A[index  +1] : Integer.MAX_VALUE;
                m = Integer.MIN_VALUE;
                for(int i = 0; i < n2; i++){
                    if(B[i] > m && B[i] > min &&  B[i] < max){
                        m = B[i];
                    }
                }
                if(m == Integer.MIN_VALUE){
                    System.out.println("NO");
                    return;
                }
            } else {
                System.out.println("NO");
                return;
            }
        }

        A[index] = m;
        for(int i = 0; i < n1 - 1; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println(A[n1 - 1]);

    }
}
