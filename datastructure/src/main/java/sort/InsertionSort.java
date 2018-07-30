package sort;

public class InsertionSort {
	
	//最好情况O(n);最坏情况O(n^2),stable
	public static void sort(int[] a) {
		int n = a.length;
		for(int i = 1; i< n; i++) {
			int temp = a[i];
			int j = i -1;
			//must check j>=0 first, then get the a[j]
			while(j >= 0 && a[j] > temp) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = temp;
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
