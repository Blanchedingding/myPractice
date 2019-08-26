package exam.qiuzhao.tengxun;

import java.util.*;

public class Test3 {

    static int result = Integer.MAX_VALUE;
    static List<Interval> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int L = sc.nextInt();
        for(int i = 0; i < n; i++){
            list.add(new Interval(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.x < o2.x) return -1;
                if(o1.x > o2.x) return 1;
                return o1.y - o2.y;
            }
        });
        Interval t = new Interval(0, 0);
        for(int i = 0; i < n; i++){
            if(list.get(i).x > t.y) {
                System.out.println("-1");
                return;
            } else {
                t.y = Math.max(t.y, list.get(i).y);
            }
        }
        if(t.y < L) {
            System.out.println("-1");
            return;
        }
        helper(new ArrayList<>(), 0, L);
        System.out.println(result);
    }

    public static void helper(List<Interval> t, int index, int L){
        if(wanzheng(t, L)){
            if(t.size() < result){
                result = t.size();
                return;
            }
        }
        t.add(list.get(index));
        helper(t, index  +1, L);
        t.remove(list.get(index));
    }

    public static boolean wanzheng(List<Interval> list, int L){
        int n = list.size();
        Interval t = new Interval(0, 0);
        for(int i = 0; i < n; i++){
            if(list.get(i).x > t.y) {
                return false;
            } else {
                t.y = Math.max(t.y, list.get(i).y);
            }
        }
        if(t.y < L) {
            return false;
        }
        return true;
    }

    static class Interval{
        int x,y;
        public Interval(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
