package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 *
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
//这道题说有个数据流每次提供一个数字，然后让我们组成一系列分离的区间
public class DataStreamasDisjointIntervals {

    public class Interval {
          int start;
          int end;
          Interval() { start = 0; end = 0; }
          Interval(int s, int e) { start = s; end = e; }
          public String toString(){
              return "[" + start + ", " + end + "]";
          }
    }

    List<Interval> result;

    /** Initialize your data structure here. */
    public DataStreamasDisjointIntervals() {
        result = new ArrayList<>();
    }

    //1. 跟现有的interval都不相交
    //2. 在其中一个内部
    //3. 与一个interval相交
    public void addNum(int val) {
        List<Interval> temp = new ArrayList<>();
        Interval p = new Interval(val, val);
        int pos = 0;
        for(Interval i: result){
            if(i.start > p.end + 1){
                temp.add(i);
            } else if(i.end + 1 < p.start){
                temp.add(i);
                pos ++;
            } else {
                p.start = Math.min(p.start, i.start);
                p.end = Math.max(p.end, i.end);
            }
        }
        temp.add(pos, p);
        result = temp;
    }

    public List<Interval> getIntervals() {
        return result;
    }

    public static void main(String[] args) {
        DataStreamasDisjointIntervals obj = new DataStreamasDisjointIntervals();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(7);
        obj.addNum(2);
        obj.addNum(6);
        List<Interval> param_2 = obj.getIntervals();
        System.out.println(param_2);
    }
}

/**
 * Your DataStreamasDisjointIntervals object will be instantiated and called as such:
 * DataStreamasDisjointIntervals obj = new DataStreamasDisjointIntervals();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */