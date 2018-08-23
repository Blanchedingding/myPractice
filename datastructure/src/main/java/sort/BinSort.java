package sort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinSort {
	
	/**
	 * 箱排序也称桶排序(Bucket Sort)，其基本思想是：设置若干个箱子，依次扫描待排序的记录R[0]，R[1]，…，R[n-1]，
	 * 把关键字等于k的记录全都装入到第k个箱子里(分配)，然后按序号依次将各非空的箱子首尾连接起来(收集)。
	 * 
	 */
	//O(n),stable
	public static void sort(int[] a, int binCount) {
		List<Integer>[] b = new LinkedList[binCount];
		for(int k = 0; k < binCount; k++) {
			b[k] = new LinkedList<Integer>();
		}
		for(int i = 0; i < a.length; i++) {
			b[a[i] / binCount].add(a[i]);
		}
		for ( int j = 0; j < binCount; j++) {
			Collections.sort(b[j]);
		}
		for(int i = 0; i < binCount; i ++) {
			for(int j = 0; j < b[i].size(); j++) {
				System.out.println(b[i].get(j));
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[] a = new int[] { 99, 65, 24, 47, 50, 88,33, 66, 67, 31, 18 };
		sort(a, 99/10 + 1);
	}

}
