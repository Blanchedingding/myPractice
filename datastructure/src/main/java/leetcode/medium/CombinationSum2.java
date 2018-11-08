package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> next = new ArrayList<>();
        //先排序
        Arrays.sort(candidates);
        helper(candidates,  result, next, 0, target);
        //利用hashset过滤重复元素
//        HashSet<List<Integer>> set = new HashSet<>(result);
//        result.clear();
//        result.addAll(set);
        return result;
    }

    public void helper(int[] candidates, List<List<Integer>> result, ArrayList<Integer> next, int index, int left){
        if(left < 0) return;
        if(left == 0 ) {
            ArrayList<Integer> t = new ArrayList<>(next);
            result.add(t);
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(left >= candidates[i] ){
                if (i > index && candidates[i] == candidates[i-1]) continue;
                next.add(candidates[i]);
                helper(candidates,result, next, i +1, left - candidates[i]);
                next.remove((Integer)candidates[i]);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum2 c = new CombinationSum2();
        int[] i = new int[]{10,1,2,7,6,1,5};
        System.out.println(Arrays.toString(c.combinationSum2(i,8).toArray()));
    }
}
