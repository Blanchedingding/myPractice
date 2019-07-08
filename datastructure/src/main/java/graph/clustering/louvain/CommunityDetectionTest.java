package graph.clustering.louvain;

import java.util.ArrayList;

public class CommunityDetectionTest {

    public void communityDetection(double[][] G){
        Louvain louvain = new Louvain();
        louvain.init(G);
        louvain.louvain();

        //打印每个节点属于哪个簇
        System.out.println("每个节点属于哪个簇:");
        for(int i=0;i<louvain.global_n;i++){
            System.out.print(Integer.toString(louvain.global_cluster[i]) + " ");
        }
        System.out.println();
        //打印每个簇有哪些节点
        System.out.println("每一行为一个簇的所有节点:");
        ArrayList list[] = new ArrayList[louvain.global_n];
        for(int i=0;i<louvain.global_n;i++){
            list[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<louvain.global_n;i++){
            list[louvain.global_cluster[i]].add(i);
        }
        for(int i=0;i<louvain.global_n;i++){
            if(list[i].size()==0) continue;
            for(int j=0;j<list[i].size();j++){
                System.out.print(list[i].get(j).toString()+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
//        double G[][] = new double[8][8];
//        G[0][1] = 3;
//        G[0][2] = 3;
//        G[0][3] = 3;
//        G[1][2] = 3;
//        G[1][3] = 3;
//        G[1][7] = 1;
//        G[2][3] = 3;
//        G[4][5] = 3;
//        G[4][6] = 2;
//        G[4][7] = 2;
//        G[5][6] = 2;
//        G[5][7] = 2;
//
//        G[1][0] = 3;
//        G[2][0] = 3;
//        G[3][0] = 3;
//        G[2][1] = 3;
//        G[3][1] = 3;
//        G[7][1] = 1;
//        G[3][2] = 3;
//        G[5][4] = 3;
//        G[6][4] = 2;
//        G[7][4] = 2;
//        G[6][5] = 2;
//        G[7][5] = 2;

        long begin = System.currentTimeMillis();
        int size = 1000;
        double[][] G = new double[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                double r = Math.random();
                if (r <= 0.44) {
                    G[i][j] = Math.random();
                    G[j][i] = G[i][j];
                }
            }
        }
        CommunityDetectionTest cd = new CommunityDetectionTest();
        cd.communityDetection(G);

        long end = System.currentTimeMillis();
        System.out.println("cost " + (end - begin) + " ms");
    }
}
