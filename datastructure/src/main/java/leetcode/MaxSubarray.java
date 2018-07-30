package leetcode;


//divide-and-conquer
public class MaxSubarray {
	
	public static int[] sub(int[] a, int left, int right) {
		if(left == right) return new int[] {left, right, a[left]};
		int mid = (left + right) /2;
		int[] leftResult = sub(a, left, mid);
		int[] rightResult = sub(a, mid+1, right);
		int[] midResult = midmax(a, left, mid, right);
		if(leftResult[2] >= rightResult[2] && leftResult[2] >= midResult[2]) {
			return leftResult;
		} else if(rightResult[2] >= leftResult[2] && rightResult[2] >= midResult[2]) {
			return rightResult;
		} else {
			return midResult;
		}
	}
	
	public static int[] midmax(int[] a, int left, int mid, int right) {
		int sum = 0;
		int leftSum = Integer.MIN_VALUE;
		int leftIndex = -1;
		for(int i = mid; i >=left; i--) {
			sum += a[i];
			if(sum > leftSum) {
				leftSum = sum;
				leftIndex = i;
			}
		}
		sum=0;
		int rightSum = Integer.MIN_VALUE;
		int rightIndex = -1;
		for(int j = mid+1; j <= right; j++) {
			sum += a[j];
			if(sum > rightSum) {
				rightSum = sum;
				rightIndex = j;
			}
		}
		return new int[] {leftIndex, rightIndex, leftSum + rightSum};
	}
	
	public static void main(String[] args) {
		//股票的每日增量，何时买入何时抛出收益最大为多少
		int[] a = new int[] {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		int[] result = sub(a,0,a.length-1);
		for(int i = 0; i < result.length; i ++) {
			//第8天买入，第11天抛出收益最高，为43
			System.out.println(result[i]);
		}
		System.out.println();
		
	}

}
