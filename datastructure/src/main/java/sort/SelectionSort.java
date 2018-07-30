package sort;

public class SelectionSort {
	
	//最好和最坏都是O(n^2),unstable
	public static void sort(int[] a) {
		int n = a.length;
		for(int i = 0; i < n-1; i++) {
			int minIndex = i;
			for(int j = i+1; j < n; j++) {
				if(a[j] < a[minIndex]) {
					minIndex = j;
				}
			}
			int temp = a[i];
			a[i] = a[minIndex];
			a[minIndex] = temp;
			
		}
		
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {31, 41, 59, 26, 41, 58};
		sort(a);
		for(int i = 0; i < a.length; i ++) {
			System.out.println(a[i]);
		}
		
	}

}
