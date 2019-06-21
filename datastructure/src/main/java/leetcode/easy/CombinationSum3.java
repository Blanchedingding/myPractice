package leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {

//    public List<List<Integer>> combinationSum3(int k, int n) {
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> next = new ArrayList<>();
//        helper(k, n, result, next, 1, 0);
//        return result;
//    }
//
//    public void helper(int leftTime, int target, List<List<Integer>> result, List<Integer> next, int index, int total){
//        if(leftTime == 0 && total == target ){
//            List<Integer> t = new ArrayList<>(next);
//            result.add(t);
//            return;
//        }
//        if(leftTime < 0 || total > target || index > 9) return;
//        for(int i = index; i <= 9; i++){
//            if(target - total < i) break;
//            next.add(i);
//            helper(leftTime - 1, target, result, next, i + 1, total + i);
//            next.remove((Integer)i);
//        }
//    }


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> temp = new ArrayList<>();
        int[] candidates = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        recursion(candidates, k, n, temp, 0, 0, 0);
        return res;
    }

    private void recursion(int[] candidates, int k, int n, List<Integer> temp,
                                  int sum, int index, int counter) {
        if (counter > k || sum > n || index > candidates.length) {
            return;
        }
        if (sum == n && k == counter && temp.size() == k) {
            Collections.sort(temp);
            if(!res.contains(temp))
                res.add(temp);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            List<Integer> t_list = new ArrayList<>(temp);
            if(!t_list.contains(candidates[i])){
                t_list.add(candidates[i]);
                recursion(candidates, k, n, t_list, sum + candidates[i], index+1, counter+1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum3 c = new CombinationSum3();
        System.out.println(c.combinationSum3(3, 9));
    }
}
