package sort;

//unstable
public class HeapSort {
	
	public static int parent(int i) {
		return (i-1)/2;
	}
	
	public static int left(int i) {
		return i*2+1;
	}
	
	public static int right(int i) {
		return i*2+2;
	}
	
	//插入元素时，从最后一个一层层向上调整
	public static void insert(int[] a, int index) {
		while(parent(index) >= 0 && a[parent(index)] < a[index]) {
			int temp = a[parent(index)];
			a[parent(index)] = a[index];
			a[index] = temp;
			index = parent(index);
		}
	}
	
	public static void build(int[] a){
		for(int i = 0; i < a.length; i++) {
			insert(a,i);
		}
	}
	
	//排序时每次取出第一个最大的之后，吧最后一个元素放到第一个，在一层层向下调整
	public static void adjust(int[] a, int lastIndex, int currentIndex) {
		int l = left(currentIndex);
		int r = right(currentIndex);
		int maxIndex = -1;
		if(l <= lastIndex && a[l] > a[currentIndex]) {
			maxIndex = l;
		} else {
			maxIndex = currentIndex;
		}
		if(r <= lastIndex && a[r] > a[maxIndex]) {
			maxIndex = r;
		}
		if(maxIndex != currentIndex) {
			swap(a, currentIndex, maxIndex);
			adjust(a, lastIndex, maxIndex);
		}
	}
	
	//O(nlgn)
	public static void sort(int[] a) {
		build(a);
		for(int i = a.length-1; i >= 0; i--) {
			System.out.println("pop: " + a[0]);
			//交换第一个和最后一个元素位置
			swap(a, 0, i);
			//一层层下调
			adjust(a, i-1, 0);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = new int[] {31, 41, 59, 26, 41, 58};
		sort(a);
	}
}
