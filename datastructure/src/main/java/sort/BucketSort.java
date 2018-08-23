package sort;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {
	/**
	 * 箱排序的变种。为了区别于上述的箱排序，姑且称它为桶排序(实际上箱排序和桶排序是同义词)。
	 * 桶排序的思想是把[0，1)划分为n个大小相同的子区间，每一子区间是一个桶。然后将n个记录分配到各个桶中。
	 * 因为关键字序列是均匀分布在[0，1)上的，所以一般不会有很多个记录落入同一个桶中。
	 * 由于同一桶中的记录其关键字不尽相同，所以必须采用关键字比较的排序方法(通常用插入排序)对各个桶进行排序，
	 * 然后依次将各非空桶中的记录连接(收集)起来即可。
	 */
	public static void sort(double[] a, int binCount) {
		List<Double>[] b = new LinkedList[binCount];
		for(int k = 0; k < binCount; k++) {
			b[k] = new LinkedList<Double>();
		}
		for(int i = 0; i < a.length; i++) {
			b[ (int)(a[i] * binCount) ].add(a[i]);		
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
		double[] a = new double[] { 0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
		sort(a, 99/10 + 1);
		
	}
}
