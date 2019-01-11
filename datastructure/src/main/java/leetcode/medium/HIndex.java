package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 *
 * Example:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndex {

    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);
        int r = -1;
        int index = -1;
        //排序后从大往小检查，找到符合条件的最大的元素下标和值
        for (int i = n - 1; i >= 0; i--){
            if(n-i >= citations[i]){
                r = citations[i];
                index = i;
                break;
            }
        }
        //如果没找到，说明元素值都很大，所以取数组长度为H
        if(r == -1) return n;
        //找到了，也有可能存在更大的H，这个H介于找到元素的值和它右边值之间
        if(index + 1 < n){
            int num = n - (index+1);//包含第index+1在内的右边的元素
            for(int j = citations[index +1]; j > r ;j--){
                if(num >= j) return j;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        HIndex h = new HIndex();
        System.out.println(h.hIndex(new int[]{3,0,6,1,5}));
        System.out.println(h.hIndex(new int[]{4,4,0,0}));
        System.out.println(h.hIndex(new int[]{1,7,9,4}));
    }
}
