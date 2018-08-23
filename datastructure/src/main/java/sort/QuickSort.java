package sort;

public class QuickSort {
	
	//最坏情况O(n^2)，平均情况O(nlgn),unstable
	public static void sort(int[] a, int left, int right) {
		if(left < right) {
			int pivot = partition(a, left, right);
			sort(a, left, pivot-1);
			sort(a, pivot+1, right);
		}
	}
	
	public static int partition(int[] a, int left, int right) {
		int x = a[right];
		int pivotIndex = left;
		for(int i = left; i < right; i++) {
			if(a[i] <= x) {
				swap(a, i, pivotIndex);
				pivotIndex++;     
			}
		}
		swap(a, right, pivotIndex);
		return pivotIndex;
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {31, 41, 59, 26, 41, 58};
		sort(a, 0, a.length-1);
		for(int i = 0; i < a.length; i ++) {
			System.out.println(a[i]);
		}
	}

}
