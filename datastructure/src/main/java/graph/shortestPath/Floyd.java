package graph.shortestPath;

/**
 * Floyd算法获取最短路径(邻接矩阵),O(V^3)
 * 负权重的边可以存在，但是没有负环
 */
public class Floyd {
    private int mEdgNum;        // 边的数量
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 最大值


    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     matrix-- 矩阵(数据)
     */
    public Floyd(char[] vexs, int[][] matrix) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;

        // 初始化"顶点"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];

        // 统计"边"
        mEdgNum = 0;
        for (int i = 0; i < vlen; i++)
            for (int j = i+1; j < vlen; j++)
                if (mMatrix[i][j]!=INF)
                    mEdgNum++;
    }

    /*
     * floyd最短路径。
     * 即，统计图中各个顶点间的最短路径。
     *
     * 参数说明：
     *     path -- 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
     *     dist -- 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
     */
    public void floyd(int[][] path, int[][] dist) {
        int[][] path2 = new int[mVexs.length][mVexs.length];

        // 初始化
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = mMatrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
                path2[i][j] = j;
            }
        }

        // 计算最短路径
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                        //第二种记录path方法
                        path2[i][j] = k;
                    }
                }
            }
        }

        // 打印floyd最短路径的结果
        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
        System.out.println();
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", path[i][j]);
            System.out.printf("\n");
        }

        int start = 0, end = 3;
        System.out.println("way1:" + start + " to " + end + " the shortest path: " + dist[start][end]);
        System.out.println("path: ");
        System.out.print(start + "->");
        printPath(start, end, path2);
        System.out.print(end);

        System.out.println();
        System.out.println("way2:" + start + " to " + end + " the shortest path: " + dist[start][end]);
        System.out.println("path: ");
        System.out.print(start + " ");
        printPath2(start, end, path2);
    }

    public void printPath(int start, int end, int[][] path){
        if(path[start][end] == end) return;
        System.out.print(path[start][end] + "->");
        printPath(path[start][end], end, path);
    }

    public void printPath2(int start, int end, int[][] path2){
        if(start == end) return;
        if(path2[start][end] == end)
            System.out.print(end + " ");
        else {
            printPath2(start, path2[start][end], path2);
            printPath2(path2[start][end], end, path2);
        }
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        Floyd pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new MatrixUDG();
        // 采用已有的"图"
        pG = new Floyd(vexs, matrix);

        int[][] path = new int[pG.mVexs.length][pG.mVexs.length];
        int[][] floy = new int[pG.mVexs.length][pG.mVexs.length];
        // floyd算法获取各个顶点之间的最短距离
        pG.floyd(path, floy);
    }
}
