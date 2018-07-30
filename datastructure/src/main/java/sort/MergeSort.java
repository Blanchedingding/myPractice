package sort;

public class MergeSort {

	//O(nlgn),stable
	public static void sort(int[] a, int left, int right) {
		//左边要比右边index小才有必要分解迭代
		if(left >= right) return;
		int mid = (left + right) / 2;
		sort(a,left, mid);
		sort(a, mid+1, right);
		merge(a,left,mid,right);
	}
	
	public static void merge(int[] a, int left, int mid, int right) {
		int n1 = mid-left+1;
		int n2 = right - mid;
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		for(int m = 0; m < n1; m++) {
			L[m] = a[left + m];
		}
		L[n1] = Integer.MAX_VALUE;
		for(int n = 0; n < n2; n++) {
			R[n] = a[mid+1+n];
		}
		R[n2] = Integer.MAX_VALUE;
		int l = 0;
		int r = 0;
		//这里的left和right都是下标，所以要取等号
		for(int i = left; i <= right; i++ ) {
			if(L[l] <= R[r]) {
				a[i] = L[l];
				l++;
			} else {
				a[i] = R[r];
				r++;
			}
		}
	}
	
	
	///////////////////////////////////////////////////////////
	//计算数组中的逆序对数量：i<j，A[i]>A[j]，则(i,j)为一个逆序对
	public static int countInversion(int[] a, int left, int right) {
		int count = 0;
		//左边要比右边index小才有必要分解迭代
		if(left >= right) return count;
		int mid = (left + right) / 2;
		count += countInversion(a,left, mid);
		count += countInversion(a, mid+1, right);
		count += mergeInversion(a,left,mid,right);
		return count;
	}
	
	public static int mergeInversion(int[] a, int left, int mid, int right) {
		int n1 = mid-left+1;
		int n2 = right - mid;
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		for(int m = 0; m < n1; m++) {
			L[m] = a[left + m];
		}
		L[n1] = Integer.MAX_VALUE;
		for(int n = 0; n < n2; n++) {
			R[n] = a[mid+1+n];
		}
		R[n2] = Integer.MAX_VALUE;
		int l = 0;
		int r = 0;
		
		int count = 0;
		boolean counted = false;
		//这里的left和right都是下标，所以要取等号
		for(int i = left; i <= right; i++ ) {
			if(counted == false && l < n1 && L[l] > R[r]) {
				//L[l]及其右边的都是比R[r]大的，全算到逆序对中
				count += n1-l;
				counted = true;
			}
			if(L[l] <= R[r]) {
				a[i] = L[l];
				l++;
			} else {
				a[i] = R[r];
				r++;
				counted = false;
			}
		}
		return count;
	}
	
	
	
	public static void main(String[] args) {
		int[] a = new int[] {31, 41, 59, 26, 41, 58};
		sort(a,0,a.length-1);
		for(int i = 0; i < a.length; i ++) {
			System.out.println(a[i]);
		}
		System.out.println();
		
		///////////////////////////////////////
		int[] b = new int[] {2,3,8,6,1};
		int count = countInversion(b,0,b.length-1);
		System.out.println(count);
		
	}
}
