package sort;

public class BubbleSort {
	
	//低效，最好情况O(n);最坏情况O(n^2)，平均O(n^2),stable
	public static void sort(int[] a) {
		int n = a.length;
		for(int i = 1; i < n; i++) {
			//设定一个标记，若为false，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
			boolean swap = false;
			for(int j = n-1; j >= i; j--) {
				if(a[j] < a[j-1]) {
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
					swap = true;
				}
			}
			if(swap == false) {
				break;
			}
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
