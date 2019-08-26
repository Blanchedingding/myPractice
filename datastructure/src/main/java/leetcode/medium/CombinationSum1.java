package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum1 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> next = new ArrayList<Integer>();
        helper(candidates, target, result, target, next, 0);
        return result;
    }

    public void helper(int[] candidates, int target, List<List<Integer>> result, int left, List<Integer> next, int index){
        if(left < 0) return;
        if(left == 0){
            result.add(next);
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(candidates[i] <= left){
                List<Integer> t = new ArrayList<>(next);
                t.add(candidates[i]);
                helper(candidates,target,result,left - candidates[i], t, i);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum1 c = new CombinationSum1();
        int[] i = new int[]{2,3,5};
        System.out.println(Arrays.toString(c.combinationSum(i,8).toArray()));
    }
}
