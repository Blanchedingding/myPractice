package leetcode.medium;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Given a set of intervals, for each of the interval i,
 * check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
 * which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index,
 * which means that the interval j has the minimum start point to build the "right" relationship for interval i.
 * If the interval j doesn't exist, store -1 for the interval i.
 * Finally, you need output the stored value of each interval as an array.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * !!!!! You may assume none of these intervals have the same start point.
 *
 *
 * Example 1:
 * Input: [ [1,2] ]
 * Output: [-1]
 *
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 *
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 * Output: [-1, 0, 1]
 *
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 *
 *
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 */
public class RightInterval {

    //使用treemap十分方便，且效率高，实际是一棵红黑树
    public int[] findRightInterval(Interval[] intervals) {
        int n = intervals.length;
        int[] result = new int[n];
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i++){
            //因为可以假设没有两个interval的start是相同的，所以用start当做key
            map.put(intervals[i].start, i);
        }
        for(int i = 0; i < n; i++){
            Map.Entry<Integer, Integer> e = map.ceilingEntry(intervals[i].end);
            result[i] = null != e? e.getValue() :-1;
        }
        return result;
    }

    public static void main(String[] args) {
        RightInterval r= new RightInterval();
        Interval[] t = new Interval[3];
        t[0] = new Interval(3,4);
        t[1] = new Interval(2,3);
        t[2] = new Interval(1,2);

        int[] i = r.findRightInterval(t);
        System.out.println(Arrays.toString(i));
    }
}

class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
 }
