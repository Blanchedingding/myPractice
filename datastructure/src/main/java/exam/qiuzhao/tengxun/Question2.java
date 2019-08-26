package exam.qiuzhao.tengxun;

import java.util.Scanner;

public class Question2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[1 << n];
        for(int i = 0; i < nums.length; i++){
            nums[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] qs = new int[m];
        for(int i = 0; i < m; i++){
            qs[i] = sc.nextInt();
        }

        for(int i = 0; i < qs.length; i++){
            nums = reverse(nums, 1 << qs[i]);
            System.out.println(InversePairs(nums));
        }
    }

    private static int[] reverse(int[] array, int m){
        int[] copy = new int[array.length];
        for(int cycle = 0; cycle < array.length / m; cycle ++){
            int i = 0;
            while(i < m){
                copy[cycle * m + m - 1 - i] = array[i + cycle * m];
                i += 1;
            }
        }
        return copy;
    }

    public static int InversePairs(int[] array) {
        if(array==null||array.length==0) {
            return 0;
        }
        int[] copy = new int[array.length];
        for(int i=0;i<array.length;i++) {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array,copy,0,array.length-1);
        return count;

    }
    private static int InversePairsCore(int[] array,int[] copy,int low,int high) {
        if(low==high) {
            return 0;
        }
        int mid = (low+high)>>1;
        int leftCount = InversePairsCore(array,copy,low,mid);
        int rightCount = InversePairsCore(array,copy,mid+1,high);
        int count = 0;
        int i=mid;
        int j=high;
        int locCopy = high;

        while(i>=low&&j>mid) {
            if(array[i]>array[j]) {
                count += j-mid;
                copy[locCopy--] = array[i--];
            } else {
                copy[locCopy--] = array[j--];
            }
        }
        for(;i>=low;i--){
            copy[locCopy--]=array[i];
        }
        for(;j>mid;j--){
            copy[locCopy--]=array[j];
        }
        return (leftCount+rightCount+count);
    }
}
