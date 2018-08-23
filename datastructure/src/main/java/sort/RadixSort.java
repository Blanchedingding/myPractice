package sort;

public class RadixSort {
	
	//d表示最大的数有多少位，然后从右到左每一位进行计数排序,stable
	public static int[] sort(int[] a, int d) {
		int[] b = new int[a.length];
		int r = 10;//10进制
		for(int i = 0, rtok = 1; i < d; i++,rtok *= r) {
			a = countingSort(a, rtok, r);
		}
		return a;
	}
	
	//O(n),stable
	//新增的r为进制，即10机制，r=10
	public static int[] countingSort(int[] a, int rtok, int r) {
		int[] b = new int[a.length];
		int[] count = new int[r];
		for(int i = 0; i < a.length; i++) {
			count[(a[i]/rtok) % r] += 1;
		}
		for(int j = 1; j < r; j++) {
			count[j] += count[j-1];
		}
		for(int m = a.length - 1; m >= 0; m --) {
			b[count[(a[m] / rtok) % r] - 1] = a[m];
			count[(a[m] / rtok) % r] --;
		}
		return b;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
		int[] b = sort(a, 3);
		for(int i = 0; i < b.length; i ++) {
			System.out.println(b[i]);
		}
	}

}
