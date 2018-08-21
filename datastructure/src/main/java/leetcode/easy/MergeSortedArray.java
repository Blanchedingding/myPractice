package leetcode.easy;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mIndex = m-1, nIndex = n-1, total = m + n -1;
        while(mIndex >= 0 && nIndex >= 0){
            if(nums1[mIndex] >= nums2[nIndex]){
                nums1[total] = nums1[mIndex];
                total --;
                mIndex --;
            } else {
                nums1[total] = nums2[nIndex];
                total --;
                nIndex --;
            }
        }
        while(nIndex >= 0){
            nums1[total] = nums2[nIndex];
            total --;
            nIndex --;
        }

    }

    public static void main(String[] args) {
        MergeSortedArray m = new MergeSortedArray();
        int[] n1 = new int[]{4,0,0,0,0,0};
        int[] n2 = new int[]{1,2,3,5,6};
        m.merge(n1, 1, n2, 5);
        System.out.println();
        Arrays.stream(n1).forEach(e-> System.out.print(e + " "));
    }
}
