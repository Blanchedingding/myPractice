package commonProblem;

import java.util.ArrayList;
import java.util.List;

//组合，从array中取num个元素
public class Combine {
	
	public static void main(String[] args) {
		int num = 3;
		int[] array = new int[] {1, 2, 4, 8, 16};
		List<String> result = new ArrayList<String>();
		helper(num, array, result, "", 0);
		
		System.out.println(result);
	}

	
	public static void helper(int num, int[] array, List<String> result, String cur, int leftIndex){
		if(num == 0) {
			result.add(cur);
			System.out.println(cur);
			return;
		}
		for(int i = leftIndex; i < array.length ; i++) {
			helper( num-1, array, result, cur + array[i] + " ", i + 1 );
		}
	}

}
