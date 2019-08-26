package exam.shixi.zhaohangshixi;

import java.util.*;

/**
 * Arthur最近搬到了新的别墅，别墅特别大，原先的桌子显得比较小，所以他决定换一张新的桌子。
 * 他买了一张特别大的桌子，桌子是由很多条桌腿进行支撑的，可是回到家之后他发现桌子不稳，原来是桌子腿长度不太相同。
 * 他想要自己把桌子修理好，所以他决定移除掉一些桌腿来让桌子变得平稳。桌子腿总共有n条腿，
 * 第i条腿长度为li，Arthur移除第i桌腿要花费代价为di。
 * 假设k条腿桌子平稳的条件:超过一半桌腿能够达到桌腿长度的最大值。
 * 例如：一条腿的桌子是平稳的，两条腿的桌子腿一样长时是平稳的。
 * 请你帮Arthur计算一下是桌子变平稳的最小总代价。 
 */
public class Test4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] ln = new int[n];
            int[] dn = new int[n];
            for (int i = 0; i < n; i++) {
                ln[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                dn[i] = in.nextInt();
            }
            int min = count(ln, dn);
            System.out.println(min);
        }
    }

    private static int count(int[] ln, int[] dn) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < ln.length; i++) {
            list.add(new Node(ln[i], dn[i]));
        }

        return count(list);
    }

    private static int count(List<Node> list) {
        List<Node> max = new ArrayList<>();
        if (list == null || list.size() == 0 || isStable(list, max)) {
            return 0;
        }

        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost < o2.cost ? 1 : -1;
            }
        });
        int cost1 = 0;
        for (int i = max.size() - 1; i < list.size(); i++) {
            cost1 += list.get(i).cost;
        }
        int cost2 = 0;
        for (int i = 0; i < max.size(); i++) {
            cost2 += max.get(i).cost;
        }

        return Math.min(cost1, cost2 + count(list));
    }

    /**
     * 判断桌子当前是否稳定
     *
     * @param list
     * @return
     */
    private static boolean isStable(List<Node> list, List<Node> max) {
        int maxLen = 0;
        for (int i = 0; i < list.size(); i++) {
            maxLen = maxLen < list.get(i).length ? list.get(i).length : maxLen;
        }
        int m = 0;
        max.clear();
        for (int i = 0; i < list.size(); i++) {
            if (maxLen == list.get(i).length) {
                m++;
                max.add(list.get(i));
            }
        }
        for (int i = 0; i < max.size(); i++) {
            list.remove(max.get(i));
        }

        return m * 2 > (list.size() + max.size());
    }

    static class Node {
        public int length;
        public int cost;

        public Node(int length, int cost) {
            this.length = length;
            this.cost = cost;
        }
    }

}
