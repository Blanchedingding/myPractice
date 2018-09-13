package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 分治法求解最近点对
 */
public class ClosestPair {

    static class Point {
        public Point() {
        }

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        double x;
        double y;
    }

    public static double distance(Point p1, Point p2) {
        if (p1 == p2)
            return 0;
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    /**
     * 返回点对之间的最小距离
     */
    public static double minDistance(Point[] ps, int l, int r) {
        /**
         * 同一个点,不存在点对,距离不能取0,返回最大值
         */
        if (l == r) {
            return Double.MAX_VALUE;
        }
        if (l + 1 == r) {
            return distance(ps[l], ps[r]);
        }
        int center = l + (r - l) / 2;
        double dis1 = minDistance(ps, l, center);
        double dis2 = minDistance(ps, center + 1, r);
        double minDis = Math.min(dis1, dis2);
        ArrayList<Point> nearPoints = new ArrayList<>();
        // 选出距离中间线小于minDis的点
        for (Point p : ps) {
            if (Math.abs(ps[center].x - p.x) <= minDis) {
                nearPoints.add(p);
            }
        }
        // 按照Y轴升序排序
        Collections.sort(nearPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.y < o2.y)
                    return -1;
                if (o1.y > o2.y)
                    return 1;
                if (o1.x < o2.x)
                    return -1;
                if (o1.x > o2.x)
                    return 1;
                return 0;
            }
        });
        for (int i = 0; i < nearPoints.size(); i++) {
            for (int j = i + 1; j < nearPoints.size(); j++) {
                if (nearPoints.get(j).y - nearPoints.get(i).y > minDis) {
                    break;// 元素y+1离元素i更远,没必要继续比较
                }
                double d = distance(nearPoints.get(j), nearPoints.get(i));
                if (d < minDis) {
                    minDis = d;
                }
            }
        }
        return minDis;
    }

    public static void main(String[] args) {
        int n = 9;
        Point[] ps = new Point[n];
        ps[0] = new Point(5,5);
        ps[1] = new Point(1,4);
        ps[2] = new Point(0,3);
        ps[3] = new Point(2.4,2);
        ps[4] = new Point(3,2);
        ps[5] = new Point(4,2);
        ps[6] = new Point(1,1);
        ps[7] = new Point(2,0);
        ps[8] = new Point(4,0);
        // 按照X轴坐标升序排序
        Arrays.sort(ps, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x < o2.x)
                    return -1;
                if (o1.x > o2.x)
                    return 1;
                if (o1.y < o2.y)
                    return -1;
                if (o1.y > o2.y)
                    return 1;
                return 0;
            }
        });
        double minDis = minDistance(ps, 0, n - 1);
        System.out.println(minDis);
    }

}
