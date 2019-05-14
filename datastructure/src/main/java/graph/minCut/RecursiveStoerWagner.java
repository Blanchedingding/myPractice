package graph.minCut;

import java.util.Arrays;

public class RecursiveStoerWagner {

    public int stoerWagner(int[][] G, boolean[] vaildVs){
        int vNum = G.length;
        if(vNum <= 0) return -1;

        int firstValid = -1;
        for(int i = 0; i < vNum; i++){
            if(vaildVs[i]) {
                firstValid = i;
                break;
            }
        }
        int vaildNum = 0;
        for(boolean b : vaildVs){
            if(b) vaildNum ++;
        }
        if(vaildNum <= 1) return -1;

        int z2 = vaildNum;
        int[] V = new int[vNum];    // v[i]代表节点i合并到的顶点
        int[] w;    // 定义w(A,x) = ∑w(v[i],x)，v[i]∈A
        boolean[] visited;    //用来标记是否该点加入了A集合
        int[] seq = new int[vaildNum];//记录移除节点的次序
        int r = 0; //记录seq的下标
        int index = -1;
        int z = vNum;
        int minCut = Integer.MAX_VALUE;

        for (int i = 0; i < vNum; i++){
            V[i] = i;  //初始还未合并，都代表节点本身
        }

        //由于原始算法只分成两份，所以默认最后一位为0，这里需要保留最后一个节点
        int lastS = -1;

        while(vaildNum > 1){
            int pre = firstValid;    //pre用来表示之前加入A集合的点（在t之前一个加进去的点）
            visited = new boolean[z];
            visited[firstValid] = true;
            w = new int[z];
            for (int i = 1; i < vaildNum; i++) {//求出某一轮最大生成树的最后两个节点，并且去除最后的t，将与t连接的边归并
                int k = -1;
                for(int j = 0; j < vNum; j++){//选取V-A中的w(A,x)最大的点x加入集合
                    if(vaildVs[V[j]] && !visited[V[j]] && V[j] != V[pre]){
                        w[V[j]] += G[V[pre]][V[j]];
                        if(k == -1 || w[V[k]] < w[V[j]]){
                            k = j;
                        }
                    }
                }
                visited[V[k]] = true;  //标记该点x已经加入A集合
                if(i == vaildNum - 1){  //若|A|=|V|（所有点都加入了A），结束
                    int s = V[pre], t = V[k];  //令倒数第二个加入A的点（v[pre]）为s，最后一个加入A的点（v[k]）为t
                    System.out.println(t + " -------> " + s);
                    lastS = s;
                    seq[r] = t;
                    System.out.println("====seq=====");
                    Arrays.stream(seq).forEach(e->System.out.print(e+" "));
                    System.out.println("\n====seq=====");
                    r ++;
                    if(w[t] < minCut){//则s-t最小割为w(A,t)，用其更新min_cut
                        minCut = w[t];
                        index = r;
                    }
                    for(int j = 0; j <z; j++){//合并s,t
                        if(vaildVs[V[j]] && V[j] != s && V[j] != t){
                            G[s][V[j]] += G[V[j]][t];
                            G[V[j]][s] += G[V[j]][t];
                        }
                    }
//                    for(int p = 0; p < z; p++){
//                        for(int q = p+1; q < z; q++){
//                            if(G[p][q] != 0){
//                                System.out.println("(" + p + " , " + q + ") = " + G[p][q]);
//                            }
//                        }
//                    }
                    vaildNum --;
                    vNum --;
                    V[k] = V[vNum]; //删除最后一个点（即删除t，也即将t合并到s）
                }
                //else继续
                pre = k;
            }
        }
        seq[r] = lastS;
        System.out.println("最小割值为：" + minCut);
        System.out.print("A部分：");
        for (int i = 0; i < z2; i++) {
            if(i == index){
                System.out.println();
                System.out.print("B部分：");
            }
            System.out.print(seq[i] + "  ");
        }
        return minCut;
    }

    public static void main(String[] args) {
        int G[][] = new int[9][9];
        G[0][1] = 3;
        G[0][2] = 3;
        G[0][3] = 3;
        G[1][2] = 3;
        G[1][3] = 3;
        G[1][7] = 1;
        G[2][3] = 3;
        G[4][5] = 3;
        G[4][6] = 2;
        G[4][7] = 2;
        G[5][6] = 2;
        G[5][7] = 2;

        G[1][0] = 3;
        G[2][0] = 3;
        G[3][0] = 3;
        G[2][1] = 3;
        G[3][1] = 3;
        G[7][1] = 1;
        G[3][2] = 3;
        G[5][4] = 3;
        G[6][4] = 2;
        G[7][4] = 2;
        G[6][5] = 2;
        G[7][5] = 2;
        RecursiveStoerWagner sw = new RecursiveStoerWagner();
//        sw.stoerWagner(G, new boolean[]{false,true,true,true, false,false,false,false});
        sw.stoerWagner(G, new boolean[]{false,false,false,false,false,true,true,false,true});
    }
}
