package exam.qiuzhao.tengxun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        int num = (int)Math.pow(2, n);
        String[] p = sc.nextLine().split(" ");
        int[] a = new int[num];
        for(int i = 0; i < num; i++){
            a[i] = Integer.valueOf(p[i]);
        }
        int m = Integer.valueOf(sc.nextLine());
        String[] c = sc.nextLine().split(" ");
        for(int i = 0; i < m; i++){
            int b = Integer.valueOf(c[i]);
            int zu = (int)Math.pow(2, b);
            if(zu > 1){
                for(int j = 0; j < num; ){
                    inverseArray(a, j, j + zu - 1);
                    j += zu;
                }
            }
            if(map.containsKey(Arrays.toString(a))){
                System.out.println(map.get(Arrays.toString(a)));
            } else {
                int u = countInversion(a.clone(), 0, num -1);
                System.out.println(u);
                map.put(Arrays.toString(a), u);
            }

        }

    }

    public static void inverseArray(int[] a, int l, int r){
        while (l < r){
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l ++;
            r --;
        }
    }

    public static int countInversion(int[] array, int l, int r){
        if(l >= r) return 0;
        int count = 0, m = (l+r)/2;
        count += countInversion(array, l, m);
        count += countInversion(array, m+1, r);
        count += mergeCount(array, l, m, r);
        return count;
    }

    public static int mergeCount(int[] array, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] b1 = new int[n1+1];
        for(int i = 0; i < n1; i++){
            b1[i] = array[i + l];
        }
        b1[n1] = Integer.MAX_VALUE;

        int[] b2 = new int[n2+1];
        for(int i = 0; i < n2; i++){
            b2[i] = array[i + m + 1];
        }
        b2[n2] = Integer.MAX_VALUE;

        int p1 = 0, p2 = 0;
        int count = 0;
        for(int i = l; i <= r; i++){
            if(b1[p1] <= b2[p2]){
                array[i] = b1[p1];
                p1++;
            } else {
                if(p1 < n1){
                    count += n1 - p1;
                }
                array[i] = b2[p2];
                p2 ++;
            }
        }
        return count;
    }
}
