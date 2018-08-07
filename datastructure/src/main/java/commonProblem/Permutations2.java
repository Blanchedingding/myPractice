package commonProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:

 Input: [1,1,2]
 Output:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 思想：当nums[i]和nums[i+1]相同的时候，保证在任意一个排列中，nums[i]总在nums[i+1]的前面，那么就不会出现重复计算排列。
 也就是说，相同的元素之间的相对位置始终保持不变，那么就不会存在因为相同元素因为相对位置互换而导致了重复，因为相同元素相对位置不变。
 */
public class Permutations2 {

    public static void main(String[] args) {
        Permutations2 p = new Permutations2();
        int[] nums = new int[]{1,1, 1, 2, 2};
        System.out.println(p.permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        //！！！先从小到大排序
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<Integer>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                //1. 如果这个数字用过了，跳过
                //2. 或者当nums[i]和nums[i+1]相同的时候，保证在任意一个排列中，nums[i]总在nums[i+1]的前面，那么就不会出现重复计算排列。
                //也就是说，相同的元素之间的相对位置始终保持不变，那么就不会存在因为相同元素因为相对位置互换而导致了重复，因为相同元素相对位置不变。
                //JAVA中&&比||优先级高
                if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;
                tempList.add(nums[i]);
                used[i] = true;
                backtrack(list, tempList, nums, used);
                tempList.remove(tempList.size() - 1);
                used[i] = false;
            }
        }
    }

}
