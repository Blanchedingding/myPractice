package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c.
 * Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed.
 * You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class FindLongestChain {

    public int findLongestChain2(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], pairs[i][0] > pairs[j][1]? dp[j] + 1 : dp[j]);
            }
        }
        return dp[pairs.length - 1];
    }

    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int begin = pairs[i][0];
            int end = pairs[i][1];
            ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
            for(ArrayList<Integer> a: list){
                //不管怎样，原来的链要保留
                list2.add(a);
                int index = 0;
                while(index < a.size() && pairs[a.get(index)][1] < begin ) index ++;
                //可以插在中间
                if(index > 0 && index < a.size() && pairs[a.get(index - 1)][1] < begin && pairs[a.get(index)][0] > end) {
                    ArrayList<Integer> h = new ArrayList<Integer>();
                    for(int j = 0; j < a.size(); j++){
                        h.add(a.get(j));
                    }
                    h.add(index, i);
                    list2.add(h);
                    continue;
                }
                //前面有一条头链，后面不符合，可以接成一条新的链，但两条链都要加进list
                if(index > 0 && pairs[a.get(index - 1)][1] < begin){
                    ArrayList<Integer> h = new ArrayList<Integer>();
                    for(int j = 0; j <= index - 1; j++){
                        h.add(a.get(j));
                    }
                    h.add(i);
                    list2.add(h);
                    continue;
                }
                //后头有一条链，前面不符合
                if(index < a.size() && pairs[a.get(index)][0] > end){
                    ArrayList<Integer> h = new ArrayList<Integer>();
                    h.add(i);
                    for(int j = index; j < a.size(); j++){
                        h.add(a.get(j));
                    }
                    list2.add(h);
                    continue;
                }
            }

            //不管怎样，这个元素自己都算一条新的链
            ArrayList<Integer> t = new ArrayList<Integer>();
            t.add(i);
            list2.add(t);

            list = list2;

//            print(list,pairs);
        }

//        print(list,pairs);
        int max = 1;
        for(ArrayList<Integer> a:list){
            if(a.size() > max) max = a.size();
        }
        return max;
    }

    public void print(ArrayList<ArrayList<Integer>> list, int[][] p){
        for(ArrayList<Integer> l: list){
            for(Integer i: l){
                System.out.print("[" + p[i][0] + ","+ p[i][1] + "]");
            }
            System.out.println();
        }
        System.out.println("===========");
    }

    public static void main(String[] args) {
        int[][] p = new int[][]{{1,2},{2,3},{3,4}};
        FindLongestChain f = new FindLongestChain();
        System.out.println(f.findLongestChain(p));
    }

}
