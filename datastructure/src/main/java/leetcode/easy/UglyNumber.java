package leetcode.easy;

import java.util.TreeSet;

public class UglyNumber {
    public static void main(String[] args) {
        UglyNumber u = new UglyNumber();
        System.out.println(u.nthUglyNumber(10));

    }

    public int nthUglyNumber(int n) {
        if(n == 1) return 1;
        TreeSet<Long> q = new TreeSet();
        q.add(1L);
        for(int i=1; i<n; i++) {
            long tmp = q.first();
            q.add(tmp*2);
            q.add(tmp*3);
            q.add(tmp*5);
            System.out.println(q);
            q.remove(tmp);
        }
        return q.first().intValue();
    }
}
