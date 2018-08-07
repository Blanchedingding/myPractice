package commonProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 */
public class Permutations {

    public static void main(String[] args) {
        Permutations p = new Permutations();
        int[] nums = new int[]{1,2,3};
        System.out.println(p.permute(nums));
    }



    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, nums,  new ArrayList<Integer>());
        return result;
    }

    private void helper( List<List<Integer>> result, int[] nums, List<Integer> list){
        if(list.size() == nums.length) {
            //！！！因为list是引用，需要new一个新的加到result里！！！！
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(! list.contains(nums[i])){
                list.add(nums[i]);
                helper(result, nums, list);
                list.remove((Integer)nums[i]);
            }
        }
    }
}
