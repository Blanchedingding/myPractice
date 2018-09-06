package graph.shortestPath;

import java.util.Scanner;

/**
 *
 * 单源最短路径，边的权重可以为负值
 * 返回一个布尔值，表明是否存在一个从源节点可以到达的权重为负值的环路，
 * 如果存在，算法将告诉我们不存在解决方；只有当这种环路不存在，算法将给出最短路径和他们的权重
 *
 * 1,.初始化：将除源点外的所有顶点的最短距离估计值 d[v] ——>+∞, d[s]——>0;
 * 2.迭代求解：反复对边集E中的每条边进行松弛操作，使得顶点集V中的每个顶点v的最短距离估计值逐步逼近其最短距离；（运行|v|-1次）
 * 3.检验负权回路：判断边集E中的每一条边的两个端点是否收敛。
 * 如果存在未收敛的顶点，则算法返回false，表明问题无解；
 * 否则算法返回true，并且从源点可达的顶点v的最短距离保存在 d[v]中。
 *
 * 算法时间复杂度O(VE)。因为算法简单，适用范围又广，虽然复杂度稍高，仍不失为一个很实用的算法。
 */
public class BellmanFord {
    public  long[] result;       //用于存放第0个顶点到其它顶点之间的最短距离

    //内部类，表示图的一条加权边
    class edge {
        public int a;   //边的起点
        public int b;   //边的终点
        public int value;  //边的权值

        edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }
    //返回第0个顶点到其它所有顶点之间的最短距离
    public  boolean getShortestPaths(int n, edge[] A) {
        result = new long[n];
        for(int i = 1;i < n;i++)
            result[i] = Integer.MAX_VALUE;  //初始化第0个顶点到其它顶点之间的距离为无穷大，此处用Integer型最大值表示
        for(int i = 1;i < n;i++) {
            for(int j = 0;j < A.length;j++) {
                if(result[A[j].b] > result[A[j].a] + A[j].value)
                    result[A[j].b] = result[A[j].a] + A[j].value;
            }
        }
        boolean judge = true;
        for(int i = 1;i < n;i++) {   //判断给定图中是否存在负环
            if(result[A[i].b] > result[A[i].a] + A[i].value) {
                judge = false;
                break;
            }
        }
        return judge;
    }

    /**
     * 请输入一个图的顶点总数n和边总数p：
     * 6 18
     * 请输入具体边的数据：
     * 0 1 6
     * 0 2 3
     * 1 2 2
     * 1 3 5
     * 2 3 3
     * 2 4 4
     * 3 4 2
     * 3 5 3
     * 4 5 5
     * 1 0 6
     * 2 0 3
     * 2 1 2
     * 3 1 5
     * 3 2 3
     * 4 2 4
     * 4 3 2
     * 5 3 3
     * 5 4 5
     *
     * 结果：0 5 3 6 7 9
     */
    public static void main(String[] args) {
        BellmanFord test = new BellmanFord();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一个图的顶点总数n和边总数p：");
        int n = in.nextInt();
        int p = in.nextInt();
        edge[] A = new edge[p];
        System.out.println("请输入具体边的数据：");
        for(int i = 0;i < p;i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int value = in.nextInt();
            A[i] = test.new edge(a, b, value);
        }
        if(test.getShortestPaths(n, A)) {
            for(int i = 0;i < test.result.length;i++)
                System.out.print(test.result[i]+" ");
        } else
            System.out.println("给定图存在负环，没有最短距离");
    }
}
