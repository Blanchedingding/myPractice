package leetcode.hard;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if((m + n) % 2 == 0){
            return (helper(nums1,0,nums2,0,(m + n) / 2) + helper(nums1,0,nums2,0,(m + n) / 2 + 1)) / 2.0;
        } else {
            return helper(nums1,0,nums2,0,(m + n) / 2+1);
        }
    }

    public double helper(int[] nums1, int begin1, int[] nums2, int begin2, int order){
        if(begin1 >= nums1.length) return nums2[begin2 + order - 1];
        if(begin2 >= nums2.length) return nums1[begin1 + order - 1];
        if(order == 1) return Math.min(nums1[begin1], nums2[begin2]);

        int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
        if(begin1 + order/2 - 1 < nums1.length) mid1 = nums1[begin1 + order/2 - 1];
        if(begin2 + order/2 - 1 < nums2.length) mid2 = nums2[begin2 + order/2 - 1];

        if(mid1 < mid2){
            return helper(nums1, begin1 + order/2, nums2, begin2, order - order/2);
        } else {
            return helper(nums1, begin1, nums2, begin2 + order/ 2, order - order/2);
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int index ;
        boolean d ;
        if((m + n) % 2 == 0){
            index = (m+n)/2 ;
            d = true;
        } else {
            index = (m+n)/2 +1;
            d = false;
        }
        boolean c = false;
        int mIndex = 0,nIndex = 0;
        int count = 0,current = -1;
        double result = 0;
        while(count <= (m+n)){
            if(count == index){
                result += (double)current;
                if(d){
                    d = false;
                    index ++;
                } else {
                    c = true;
                }
            }
            if(c) break;
            if(mIndex >= m){
                current = nums2[nIndex];
                nIndex++;
            } else if(nIndex >= n){
                current = nums1[mIndex];
                mIndex++;
            } else if(nums1[mIndex] <= nums2[nIndex]){
                current = nums1[mIndex];
                mIndex++;
            } else {
                current = nums2[nIndex];
                nIndex++;
            }
            count++;
        }
        if((m + n) % 2 == 0){
            return result/2;
        } else {
            return result;
        }

    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
//        System.out.println(m.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(m.findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6}));
    }
}
