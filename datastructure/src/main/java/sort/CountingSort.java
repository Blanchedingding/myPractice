package sort;

public class CountingSort {
	
	//O(n),stable
	//a数组为0-k之间的整数数组
	public static int[] sort(int[] a, int k) {
		int[] b = new int[a.length];
		int[] count = new int[k];
		for(int i = 0; i < a.length; i++) {
			count[a[i]] += 1;
		}
		for(int j = 1; j < k; j++) {
			count[j] += count[j-1];
//			System.out.println(count[j]);
		}
		for(int m = a.length - 1; m >= 0; m --) {
			b[count[a[m]]-1] = a[m];
			count[a[m]] --;
		}
		return b;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {2, 5, 3, 0, 2, 3, 0, 3};
		int[] b = sort(a, 6);
		for(int i = 0; i < b.length; i ++) {
			System.out.println(b[i]);
		}
		
	}
	

}
