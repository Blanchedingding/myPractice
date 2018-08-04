package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values,
 * compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 *
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
 * All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 *
 * Example:
 *
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * Output:
 * 2
 *
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSum2 {



    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> map2 = new HashMap<Integer, Integer>();
        int r = 0;
        for(int i = 0; i < A.length; i ++){
            for(int j = 0; j < B.length; j ++){
                int a = A[i] + B[j];
                map2.put(a, (map2.containsKey(a)? map2.get(a):0) + 1);
            }
        }

        for(int i = 0; i < C.length; i ++){
            for(int j = 0; j < D.length; j ++){
               r += map2.containsKey(-C[i] - D[j])? map2.get(-C[i] - D[j]):0;
            }
        }

        return r;
    }

    Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int r = 0;
        for(int i = 0; i < A.length; i ++){
            for(int j = 0; j < B.length; j ++){
                int remain = 0- A[i] - B[j];
                r += helper(C,D,remain);
                System.out.println(remain + " " + r);
            }
        }

        return r;

    }

    public int helper(int[] C, int[] D, int remain){
        if(map.get(remain)!= null){
            return map.get(remain);
        }else {
            int c = 0;
            int m = 0, n = D.length - 1;
            while(m < C.length && n >= 0){
                int t = C[m] + D[n];
                if(t == remain){
                    int p = 1, q = 1;
                    while(m < C.length -1 && n >= 0 && C[m +1] == C[m]){
                        p ++;
                        m++;
                    }
                    while(m < C.length && n > 0 && D[n-1] == D[n]){
                        q ++;
                        n--;
                    }
                    c += p * q;
                    m ++;
                    n --;
                } else if(t < remain){
                    m ++;
                } else {
                    n --;
                }
            }
            map.put(remain, c);
            return c;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2};
        int[] b = new int[]{-2,-1};
        int[] c = new int[]{-1,2};
        int[] d = new int[]{0,2};
        FourSum2 s = new FourSum2();
        int r = s.fourSumCount(a,b,c,d);
        System.out.println(r);
    }
}
